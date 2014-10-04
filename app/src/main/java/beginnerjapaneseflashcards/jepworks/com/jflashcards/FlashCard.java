package beginnerjapaneseflashcards.jepworks.com.jflashcards;

/**
 * Created by James on 10/3/2014.
 */
public class FlashCard {

    private int hirigana;
    private int katakana;
    private String english;
    private String romaji;

    public int getHirigana() {
        return hirigana;
    }

    public void setHirigana(int hirigana) {
        this.hirigana = hirigana;
    }

    public int getKatakana() {
        return katakana;
    }

    public void setKatakana(int katakana) {
        this.katakana = katakana;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public FlashCard(int hirigana, int katakana, String english, String romaji) {
        this.hirigana = hirigana;
        this.katakana = katakana;
        this.english = english;
        this.romaji = romaji;
    }
}
