package intoDarkness.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import intoDarkness.DefaultMod;
import intoDarkness.util.TextureLoader;

import static intoDarkness.DefaultMod.makeRelicOutlinePath;
import static intoDarkness.DefaultMod.makeRelicPath;

public class TestRelic extends CustomRelic {
    public static final String ID = DefaultMod.makeID("TestRelic");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("TestRelic.png"));

    public TestRelic() { super(ID, IMG, RelicTier.COMMON, LandingSound.MAGICAL); }

    @Override
    public void atBattleStart() {
        flash();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1]; // DESCRIPTIONS pulls from your localization file
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.decreaseMaxHealth(1);
    }

    @Override
    public void onUnequip() {
        
    }
}
