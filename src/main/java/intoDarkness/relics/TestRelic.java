package intoDarkness.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomBottleRelic;
import basemod.abstracts.CustomRelic;
import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.common.AutoplayCardAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theDefault.DefaultMod;
import theDefault.patches.relics.BottledPlaceholderField;
import theDefault.util.TextureLoader;

import java.util.Iterator;
import java.util.function.Predicate;

import static theDefault.DefaultMod.makeRelicOutlinePath;
import static theDefault.DefaultMod.makeRelicPath;

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
        AbstractDungeon.player.increaseMaxhealth(1);
    }
}
