package intoDarkness.monsters;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class OmenOfDarkness extends AbstractMonster
{
    public static final String ID = "intoDarkness:OmenOfDarkness";
    public static final String NAME = "Omen Of Darkness";
    public static final String[] MOVES = {};
    public static final int HP = 6;

    private static final byte LOAD_CANNON = 0;
    private static final byte FIRE_CANNON = 1;
    private static final byte WOUNDING_SHOT = 2;
    private static final byte NORMAL_SHOT = 3;


    private int numTurns = 0;

    private boolean firstTurn = true;
    private int woundAmt;


    public OmenOfDarkness()
    {
        super(NAME, ID, HP, -8.0f, 80.0f, 330, 530, "intoDarknessResources/images/monsters/monster_placeholder.png", -100.0f, -100.0f);

        this.type = EnemyType.BOSS; //todo figure out how to make it the first
        this.dialogX = (-400.0F * Settings.scale);
        this.dialogY = (200.0F * Settings.scale);

        damage.add(0, new DamageInfo(this, 50));
        damage.add(1, new DamageInfo(this, 10));
        damage.add(2, new DamageInfo(this, 16));

        woundAmt = 1;
    }

    @Override
    public void usePreBattleAction()
    {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_CITY");
        //(adds new pow?)AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new ArmorPiercingPower(this)));
    }

    private void queueDamageAction(DamageInfo damageInfo)
    {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, damageInfo));
    }

    @Override
    public void takeTurn()
    {

        if (nextMove == WOUNDING_SHOT) {
            queueDamageAction(damage.get(1));

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this,
                    new WeakPower(AbstractDungeon.player, woundAmt, true), woundAmt));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this,
                    new FrailPower(AbstractDungeon.player, woundAmt, true), woundAmt));
        } else if (1==1) {
            queueDamageAction(damage.get(2));
        }

        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) /* choses attack */
    {
            //Use if statments and stuff
            setMove(NORMAL_SHOT, Intent.ATTACK, damage.get(2).base);


        ++numTurns;
    }

    @Override
    public void update() //fancy effects?
    {
        super.update();
    }

    @Override
    public void die()
    {
        useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        deathTimer += 1.5F;
        super.die();
        int roll = MathUtils.random(2);
        if (roll == 0) {
            CardCrawlGame.sound.play("VO_CULTIST_2A");
        } else if (roll == 1) {
            CardCrawlGame.sound.play("VO_CULTIST_2B");
        } else {
            CardCrawlGame.sound.play("VO_CULTIST_2C");
        }
        onBossVictoryLogic();
    }
}