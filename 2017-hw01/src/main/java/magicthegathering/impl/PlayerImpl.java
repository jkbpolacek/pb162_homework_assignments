package magicthegathering.impl;


import magicthegathering.game.Card;
import magicthegathering.game.CreatureCard;
import magicthegathering.game.LandCard;
import magicthegathering.game.Player;
import magicthegathering.game.ManaType;

import java.util.Arrays;

/**
 * @author Jakub Polacek
 */

public class PlayerImpl implements Player {

    /**
     * players life
     */
    private int currentLife;
    /**
     * players all owned cards
     */
    private Card[] cards;
    /**
     * players name
     */
    private String name;

    /**
     * constructor for player
     *
     * @param name players name
     */
    public PlayerImpl(String name) {
        this.name = name;
        this.currentLife = INIT_LIVES;
    }

    /**
     * does this really need documentation
     *
     * @return current life
     */

    @Override
    public String toString() {
        return name + "(" + Integer.toString(currentLife) + ")";
    }

    @Override
    public int getLife() {
        return currentLife;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * remove given amout from life
     *
     * @param lives amount to remove
     */
    @Override
    public void subtractLives(int lives) {
        currentLife -= lives;

    }

    @Override
    public boolean isDead() {
        return (currentLife <= 0);
    }

    @Override
    public void initCards(Card[] cards) {
        this.cards = Arrays.copyOf(cards, cards.length);
    }

    @Override
    public Card[] getCardsInHand() {
        return ArrayUtils.filterInHand(cards);
    }

    @Override
    public Card[] getCardsOnTable() {
        return ArrayUtils.filterOnTable(cards);
    }

    @Override
    public LandCard[] getLandsOnTable() {
        return ArrayUtils.filterLands(getCardsOnTable());
    }

    @Override
    public CreatureCard[] getCreaturesOnTable() {
        return ArrayUtils.filterCreatures(getCardsOnTable());
    }

    @Override
    public LandCard[] getLandsInHand() {
        return ArrayUtils.filterLands(getCardsInHand());
    }

    @Override
    public CreatureCard[] getCreaturesInHand() {
        return ArrayUtils.filterCreatures(getCardsInHand());
    }

    @Override
    public void untapAllCards() {
        for (Card card : getCardsOnTable()) {
            card.untap();
        }
    }

    @Override
    public void prepareAllCreatures() {
        for (CreatureCard creature : getCreaturesOnTable()) {
            creature.unsetSummoningSickness();
        }
    }

    @Override
    public boolean putLandOnTable(LandCard landCard) {
        if ((landCard.isOnTable()) || !ArrayUtils.containsCard(landCard, cards)) {
            return false;
        }
        landCard.putOnTable();
        return true;
    }

    @Override
    public int[] calculateUntappedLands() {

        int[] result = new int[5];

        // not sure if i have to initialize but lets do it anyways
        for (int i = 0; i < 5; i++) {
            result[i] = 0;
        }

        for (LandCard land : getLandsOnTable()) {
            if (!land.isTapped()) {
                result[land.getManaType().ordinal()] += 1;
            }
        }

        return result;
    }

    @Override
    public boolean hasManaForCreature(CreatureCard creature) {

        int[] manas = calculateUntappedLands();

        for (ManaType mana : ManaType.values()) {


            if (manas[mana.ordinal()] < creature.getSpecialCost(mana)) {
                return false;
            }
        }
        return true;

    }


    @Override
    public void tapManaForCreature(CreatureCard creature) {

        if (hasManaForCreature(creature)) {

            LandCard[] lands = getLandsOnTable();

            for (ManaType mana : ManaType.values()) {

                int unpaidPrice = creature.getSpecialCost(mana);
                int i = 0;

                while (unpaidPrice > 0 && i < lands.length) {

                    if ((lands[i].getManaType() == mana) && !lands[i].isTapped()) {
                        lands[i].tap();
                        unpaidPrice--;
                    }

                    i++;
                }
            }
        }
    }

    @Override
    public boolean putCreatureOnTable(CreatureCard creatureCard) {
        if (hasManaForCreature(creatureCard) && (!creatureCard.isOnTable())
                && (ArrayUtils.containsCard(creatureCard, cards))) {

            tapManaForCreature(creatureCard);
            creatureCard.setSummoningSickness();
            creatureCard.putOnTable();
            return true;
        }
        return false;
    }

    @Override
    public void destroyCreature(CreatureCard creature) {
        cards = ArrayUtils.removeCard(creature, cards);
    }
}
