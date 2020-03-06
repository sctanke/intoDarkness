package intoDarkness.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import intoDarkness.DefaultMod;
import intoDarkness.util.TextureLoader;

import static intoDarkness.DefaultMod.makeRelicOutlinePath;
import static intoDarkness.DefaultMod.makeRelicPath;

public class Bloodthirster extends CustomRelic {
    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain lifesteal
     */

    // ID, images, text.
    public static final String ID = DefaultMod.makeID("Bloodthirster");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic2.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic2.png"));

    public Bloodthirster() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.FLAT);
    }


    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        flash();
        AbstractDungeon.actionManager.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, 1));
    }


    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
