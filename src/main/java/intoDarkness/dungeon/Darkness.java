package intoDarkness.dungeon;

public class Darkness extends CustomDungeon {
    public static final String ID = MyAct.makeID("YOURACT"); //From the main mod file for best practices
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;
    public static final String NAME = TEXT[0];

    public Darkness() {
        super(NAME : String, ID : String);
    }

    public Darkness(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) {
        super(cd, p, emptyList);
    }
    public Darkness(CustomDungeon cd, AbstractPlayer p, SaveFile sf) {
        super(cd, p, sf);
    }
}