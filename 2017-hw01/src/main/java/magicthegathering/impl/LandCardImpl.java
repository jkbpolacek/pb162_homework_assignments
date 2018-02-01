package magicthegathering.impl;

import magicthegathering.game.AbstractCard;
import magicthegathering.game.LandCard;
import magicthegathering.game.LandCardType;
import magicthegathering.game.ManaType;


/**
 * @author Jakub Polacek
 */

public class LandCardImpl extends AbstractCard implements LandCard {


    /**
     * type of land
     */
    private LandCardType landCardType;


    /**
     * type of mana provided by land
     */
    private ManaType manaType;


    /**
     * Constructor for landCard
     *
     * @param landCardType type of land
     */
    public LandCardImpl(LandCardType landCardType) {
        this.landCardType = landCardType;
        this.manaType = ManaType.values()[landCardType.ordinal()];

    }

    @Override
    public LandCardType getLandType() {
        return this.landCardType;
    }

    @Override
    public ManaType getManaType() {
        return this.manaType;
    }

    @Override
    public String toString() {

        return "Land " + this.getLandType().name().toLowerCase() + ", " + this.getManaType().name();

    }


}


