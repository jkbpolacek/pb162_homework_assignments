package magicthegathering.impl;

import magicthegathering.game.Card;
import magicthegathering.game.CreatureCard;
import magicthegathering.game.LandCard;

import java.util.Arrays;


/**
 * @author Jakub Polacek
 */

public class ArrayUtils {


    /**
     * Counts card of a given class type
     * @param cards array of cards to count from
     * @param type which type to look for
     * @return number of cards of given type
     */

    private static int countCards(Card[] cards, Class<?> type){

        int cardCount = 0;

        for (Card card : cards) {
            if (type.isInstance(card)) {
                cardCount++;
            }

        }

        return cardCount;

    }


    /**
     * Filter out all cards of subclass landcard
     *
     * @param cards array of cards
     * @return array of filterd cards
     */

    public static LandCard[] filterLands(Card[] cards) {

        int cardCount = countCards(cards, LandCard.class);


        LandCard[] result = new LandCard[cardCount];



        int i = 0;
        for (int j = 0; j < cards.length; j++) {

            if (cards[j] instanceof LandCard) {
                result[i] = (LandCard) cards[j];
                i++;
            }
        }

        return result;
    }




    /**
     * Filter all creature cards
     *
     * @param cards array of cards
     * @return array of filtered cards
     */
    public static CreatureCard[] filterCreatures(Card[] cards) {

        int cardCount = countCards(cards, CreatureCard.class);

        CreatureCard[] result = new CreatureCard[cardCount];

        int i = 0;
        for (int j = 0; j < cards.length; j++) {

            if (cards[j] instanceof CreatureCard) {
                result[i] = (CreatureCard) cards[j];
                i++;
            }
        }

        return result;
    }

    /**
     * counts cards on table
     * @param cards all cards
     * @return number of cards on table
     */

    private static int countTableCards(Card[] cards){

        int cardCount = 0;
        for (Card card : cards) {
            if (card.isOnTable()) {
                cardCount++;
            }

        }

        return cardCount;
    }


    /**
     * return all cards that are not on table
     *
     * @param cards array of cards
     * @return array of cards not on table
     */
    public static Card[] filterInHand(Card[] cards) {

        int cardCount = cards.length - countTableCards(cards);

        Card[] result = new Card[cardCount];

        int i = 0;
        for (int j = 0; j < cards.length; j++) {

            if (!(cards[j].isOnTable())) {
                result[i] = cards[j];
                i++;
            }
        }

        return result;
    }

    /**
     * filter all cards that are on table
     *
     * @param cards array of cards
     * @return filtered array of cards on table
     */
    public static Card[] filterOnTable(Card[] cards) {

        int cardCount = countTableCards(cards);


        Card[] result = new Card[cardCount];

        int i = 0;
        for (int j = 0; j < cards.length; j++) {

            if (cards[j].isOnTable()) {
                result[i] = cards[j];
                i++;
            }
        }

        return result;
    }

    /**
     * Using findCardIndex and the principle that if there are two duplicate cards,
     * one of them must have come before the other, check for duplicates
     *
     * @param cards a hand of cards to check
     * @return answer whether there are duplicates
     */

    public static boolean hasDuplicatesExceptNull(Card[] cards) {

        for (int i = cards.length - 1; i >= 0; i--) {
            if ((cards[i] != null) && (findCardIndex(cards[i], cards) != i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * look if the card is there
     *
     * @param searchedCard card we are looking for
     * @param cards        a hand of cards to check
     * @return answer true or false
     */

    public static boolean containsCard(Card searchedCard, Card[] cards) {

        return (findCardIndex(searchedCard, cards) != -1);
    }

    /**
     * get searched card index or -1 if not found
     *
     * @param searchedCard card we are looking for
     * @param cards        a hand of cards to check
     * @return index
     */
    public static int findCardIndex(Card searchedCard, Card[] cards) {


        for (int i = 0; i < cards.length; i++) {
            if (cards[i] == searchedCard) {
                return i;
            }
        }

        return -1;
    }


    /**
     * remove unwanted card from hand
     *
     * @param unwantedCard card we dont want
     * @param cards        hand of cards to check
     * @return hand of cards with removed unwantend card
     */
    public static Card[] removeCard(Card unwantedCard, Card[] cards) {



        // replace the card to remove in our array with last card from array, then copy all but the last card

        int i = findCardIndex(unwantedCard, cards);
        cards[i] = cards[cards.length - 1];

        Card[] result = Arrays.copyOf(cards, cards.length - 1);
        return result;

    }

}
