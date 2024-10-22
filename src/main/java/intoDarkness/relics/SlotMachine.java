package intoDarkness.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import intoDarkness.DefaultMod;
import intoDarkness.util.TextureLoader;

import java.util.Random;

import static intoDarkness.DefaultMod.makeRelicOutlinePath;
import static intoDarkness.DefaultMod.makeRelicPath;

public class SlotMachine extends CustomRelic implements ClickableRelic { // You must implement things you want to use from StSlib
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     * StSLib for Clickable Relics
     *
     * Usable once per turn. Right click: Evoke your rightmost orb.
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("SlotMachine");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("default_clickable_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("default_clickable_relic.png"));

    private boolean usedThisTurn = false; // You can also have a relic be only usable once per combat. Check out Hubris for more examples, including other StSlib things.
    private boolean isPlayerTurn = false; // We should make sure the relic is only activateable during our turn, not the enemies'.

    public SlotMachine() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.CLINK);

        tips.clear();
        tips.add(new PowerTip(name, description));
    }


    @Override
    public void onRightClick() {// On right click
        if (!isObtained || usedThisTurn || !isPlayerTurn) {
            // If it has been used this turn, the player doesn't actually have the relic (i.e. it's on display in the shop room), or it's the enemy's turn
            return; // Don't do anything.
        }
        
        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) { // Only if you're in combat
            usedThisTurn = true; // Set relic as "Used this turn"
            flash(); // Flash
            stopPulse(); // And stop the pulsing animation (which is started in atPreBattle() below)
            Random rand = new Random();
            int myRandom = rand.nextInt(100) + 1;
            AbstractDungeon.actionManager.addToBottom(new GainGoldAction(-25));
            if (myRandom<5) {
                AbstractDungeon.actionManager.addToBottom(new GainGoldAction(100));
            }
            else if (myRandom >= 5 && myRandom <45) {

                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, 5));
            }
            else if (myRandom >= 45 && myRandom < 50) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 20), 5));
            }
            else if (myRandom >= 50 && myRandom < 70)
                    AbstractDungeon.actionManager.addToBottom(new LoseHPAction(AbstractDungeon.player, AbstractDungeon.player,15));
            else{
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new WeakPower(AbstractDungeon.player,1,false)));
            }
            //AbstractDungeon.actionManager.addToBottom(new TalkAction(true, DESCRIPTIONS[1], 4.0f, 2.0f)); // Player speech bubble saying "YOU ARE MINE!" (See relic strings)
            //AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_DEBUFF")); // Sound Effect Action of The Collector Nails
            //AbstractDungeon.actionManager.addToBottom(new VFXAction( // Visual Effect Action of the nails applies on a random monster's position.
                    //new CollectorCurseEffect(AbstractDungeon.getRandomMonster().hb.cX, AbstractDungeon.getRandomMonster().hb.cY), 2.0F));

            //AbstractDungeon.actionManager.addToBottom(new EvokeOrbAction(1)); // Evoke your rightmost orb
        }
        // See that talk action? It has DESCRIPTIONS[1] instead of just hard-coding "You are mine" inside.
        // DO NOT HARDCODE YOUR STRINGS ANYWHERE, it's really bad practice to have "Strings" in your code:

        /*
         * 1. It's bad for if somebody likes your mod enough (or if you decide) to translate it.
         * Having only the JSON files for translation rather than 15 different instances of "Dexterity" in some random cards is A LOT easier.
         *
         * 2. You don't have a centralised file for all strings for easy proof-reading. If you ever want to change a string
         * you don't have to go through all your files individually/pray that a mass-replace doesn't screw something up.
         *
         * 3. Without hardcoded strings, editing a string doesn't require a compile, saving you time (unless you clean+package).
         *
         */
    }
    
    @Override
    public void atPreBattle() {
        usedThisTurn = false; // Make sure usedThisTurn is set to false at the start of each combat.
        beginLongPulse();     // Pulse while the player can click on it.
    }

    public void atTurnStart() {
        usedThisTurn = false;  // Resets the used this turn. You can remove this to use a relic only once per combat rather than per turn.
        isPlayerTurn = true; // It's our turn!
        beginLongPulse(); // Pulse while the player can click on it.
    }
    
    @Override
    public void onPlayerEndTurn() {
        isPlayerTurn = false; // Not our turn now.
        stopPulse();
    }
    

    @Override
    public void onVictory() {
        stopPulse(); // Don't keep pulsing past the victory screen/outside of combat.
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
