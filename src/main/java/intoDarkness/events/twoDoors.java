package intoDarkness.events;

import static intoDarkness.DefaultMod.makeEventPath;

public class twoDoors extends AbstractImageEvent {
    public static final String IMG = makeEventPath("IdentityCrisisEvent.png");
    public static final String ID = "twoDoors";
    private int screenNum = 0;
    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;

    public twoDoors(){
        super(ID, "My body text", IMG);
        this.imageEventText.setDialogOption("Light Door");
        this.imageEventText.setDialogOption("My Dialog Option");
    }
    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                switch (i) {
                    case 0:
                        this.imageEventText.updateBodyText("");
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 1;

                        AbstractCard c = new TestRelic.makeCopy();
                        AbstractDungeon.effectList.add(
                                new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
                        break;
                    case 1:
                        screenNum = 1;
                        break;
                    //does not work
                }
            case 1:
                switch (i) {
                    case 0: // If you press the first (and this should be the only) button,
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
        }
    }
}


