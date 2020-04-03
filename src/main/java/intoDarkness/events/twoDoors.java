package intoDarkness.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import intoDarkness.DefaultMod;

import static intoDarkness.DefaultMod.makeEventPath;


public class twoDoors extends AbstractImageEvent {
    public static final String IMG = makeEventPath("IdentityCrisisEvent.png");
    public static final String ID = "twoDoors";
    private int screenNum = 0;
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
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
                switch (buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText("you see a door of light");
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();

                        screenNum = 1;

                        //AbstractCard c = new TestRelic.makeCopy();
                        //AbstractDungeon.effectList.add(
                                //new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));

                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f,
                                RelicLibrary.getRelic(TestRelic.ID).makeCopy());


                        break;
                    case 1:
                        this.imageEventText.updateBodyText("you see a door of darkness");
                        this.imageEventText.updateDialogOption(0, OPTIONS[5]);
                        this.imageEventText.clearRemainingOptions();
                        
                        screenNum = 1;

                        //c = new TestRelic.makeCopy();
                        //AbstractDungeon.effectList.add(
                                //new ShowCardAndObtainEffect(c, (float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2)));
                        break;

                }
            case 1:
                switch (buttonPressed) {
                    case 0: // If you press the first (and this should be the only) button,
                        openMap(); // You'll open the map and end the event.
                        break;
                }
                break;
        }
    }
}


