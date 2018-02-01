package magicthegathering.impl;

import magicthegathering.game.CreatureCard;
import magicthegathering.game.Game;
import magicthegathering.game.Generator;
import magicthegathering.game.Player;

/**
 * @author Jakub Polacek
 */
public class GameImpl implements Game {

    /**
     * Player playing his turn.
     */
    private Player currentPlayer;
    /**
     * Player playing his turn next.
     */
    private Player nextPlayer;

    /**
     * Constructor for game impl
     *
     * @param currentPlayer first player
     * @param nextPlayer    second player
     */
    public GameImpl(Player currentPlayer, Player nextPlayer) {
        this.currentPlayer = currentPlayer;
        this.nextPlayer = nextPlayer;
    }

    @Override
    public void initGame() {
        currentPlayer.initCards(Generator.generateCards());
        nextPlayer.initCards(Generator.generateCards());
    }

    @Override
    public void changePlayer() {
        Player preparingPlayer = nextPlayer;
        nextPlayer = currentPlayer;
        currentPlayer = preparingPlayer;
        prepareCurrentPlayerForTurn();

    }

    @Override
    public void prepareCurrentPlayerForTurn() {
        currentPlayer.prepareAllCreatures();
        currentPlayer.untapAllCards();
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public Player getSecondPlayer() {
        return nextPlayer;
    }

    @Override
    public void performAttack(CreatureCard[] creatures) {

        if (isCreaturesAttackValid(creatures)) {

            for (CreatureCard creature : creatures) {
                creature.tap();
            }

        }

    }

    @Override
    public boolean isCreaturesAttackValid(CreatureCard[] attackingCreatures) {

        if (ArrayUtils.hasDuplicatesExceptNull(attackingCreatures)) {
            return false;
        }

        for (CreatureCard creature : attackingCreatures) {

            if (creature != null) {

                if (!(creature.isOnTable()) || (creature.isTapped()) || (creature.hasSummoningSickness())
                        || !(ArrayUtils.containsCard(creature, currentPlayer.getCreaturesOnTable()))) {
                    return false;
                }

            }

        }


        return true;
    }

    @Override
    public boolean isCreaturesBlockValid(CreatureCard[] attackingCreatures, CreatureCard[] blockingCreatures) {

        boolean lengthCheck = (attackingCreatures.length == blockingCreatures.length);
        boolean attackDuplicatesCheck = (ArrayUtils.hasDuplicatesExceptNull(attackingCreatures));
        boolean blockDuplicatesCheck = (ArrayUtils.hasDuplicatesExceptNull(blockingCreatures));

        if (!(lengthCheck) || attackDuplicatesCheck || blockDuplicatesCheck) {

            return false;
        }

        for (CreatureCard creature : attackingCreatures) {
            if (!ArrayUtils.containsCard(creature, currentPlayer.getCreaturesOnTable())) {
                return false;
            }
        }

        for (CreatureCard creature : blockingCreatures) {
            if (creature != null) {
                if (!ArrayUtils.containsCard(creature, nextPlayer.getCreaturesOnTable()) || creature.isTapped()) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Function to figure out whether deffending creature gets killed by aggressor
     *
     * @param damager  the attacking creature
     * @param defender the deffending creature
     * @return true or false on whether defender got brutally murdered
     */
    private boolean checkCreatureKill(CreatureCard damager, CreatureCard defender) {
        return (defender.getToughness() <= damager.getPower());
    }

    /**
     * Have creatures fight and kill each other for our entertainment
     *
     * @param attacker attacking current players creature
     * @param blocker  blocking next players creature
     */

    private void creatureDuel(CreatureCard attacker, CreatureCard blocker) {
        if (checkCreatureKill(attacker, blocker)) {
            nextPlayer.destroyCreature(blocker);
        }

        if (checkCreatureKill(blocker, attacker)) {
            currentPlayer.destroyCreature(attacker);
        }
    }

    @Override
    public void performBlockAndDamage(CreatureCard[] attackingCreatures, CreatureCard[] blockingCreatures) {
        if (isCreaturesBlockValid(attackingCreatures, blockingCreatures)) {

            for (int i = 0; i < attackingCreatures.length; i++) {

                if (blockingCreatures[i] != null) {

                    creatureDuel(attackingCreatures[i], blockingCreatures[i]);

                } else {
                    nextPlayer.subtractLives(attackingCreatures[i].getPower());
                }

            }
        }
    }
}
