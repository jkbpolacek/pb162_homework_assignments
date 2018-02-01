package magicthegathering.impl;

import magicthegathering.game.AbstractCard;
import magicthegathering.game.CreatureCard;
import magicthegathering.game.ManaType;

import java.util.Arrays;

/**
 * @author Jakub Polacek
 */

public class CreatureCardImpl extends AbstractCard implements CreatureCard {

    /**
     * name of ze creature
     */
    private String name;

    /**
     * array of required manas
     */
    private ManaType[] cost;
    /**
     * creatures attack power
     */
    private int power;
    /**
     * creatures ability to withstand a beating
     */
    private int toughness;
    /**
     * creatures readiness to fight
     */
    private boolean summoningSickness;

    /**
     * constructor for creature card
     *
     * @param name      its name
     * @param cost      its cost
     * @param power     its power
     * @param toughness its toughness
     */
    public CreatureCardImpl(String name, ManaType[] cost, int power, int toughness) {
        this.name = name;
        this.cost = cost;
        this.power = power;
        this.toughness = toughness;
    }

    /**
     * get a sum of costs
     *
     * @return total number of required manas of any type
     */
    public int getTotalCost() {
        return cost.length;
    }


    @Override
    public int getSpecialCost(ManaType mana) {

        int specialCost = 0;
        for (ManaType costMana : cost) {
            if (costMana == mana) {
                specialCost++;
            }
        }

        return specialCost;
    }

    @Override
    public String getName() {
        return name;
    }


    /**
     * microfunction to create a list of required manas for creature
     *
     * @return a string of mana types separated by a semicolon
     */
    private String manas() {

        /*
        String manas = "";

        for (int i = 0; i < cost.length; i++) {
            manas += cost[i].name();
            if (i < cost.length - 1) {
                manas += ", ";
            }
        }
        */

        return Arrays.toString(cost);
    }

    @Override
    public String toString() {


        String canAttack = hasSummoningSickness() ? "" : " can attack";
        String tapped = isTapped() ? " TAPPED" : "";


        String result = getName() + " " + manas() + " " + Integer.toString(getPower());
        result += " / " + Integer.toString(getToughness()) + canAttack + tapped;

        return result;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public int getToughness() {
        return toughness;
    }

    @Override
    public boolean hasSummoningSickness() {
        return summoningSickness;
    }

    @Override
    public void setSummoningSickness() {
        summoningSickness = true;
    }

    @Override
    public void unsetSummoningSickness() {
        summoningSickness = false;
    }

}
