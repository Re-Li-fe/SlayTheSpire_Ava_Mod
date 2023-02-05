package characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import demoMod.AvaMod;
import pathes.AbstractCardEnum;
import pathes.ThmodClassEnum;

import java.util.ArrayList;

public class DefaultCharacter extends CustomPlayer {

    public static final int ENERGY_PER_TURN = 3; //每回合能量数
    public static final String AVA_SHOULDER_2 = "img/char/shoulder2.png"; // 休息时背景
    public static final String AVA_SHOULDER_1 = "img/char/shoulder1.png"; // 休息时背景2
    public static final String AVA_CORPSE = "img/char/corpse.png"; // 死亡姿势
    public static final String AVA_STAND = "img/char/stand.png"; // 战立姿势

    //能量背景
    public static final String[] orbTextures = new String[] { "img/char/orb/layer1.png", "img/char/orb/layer2.png", "img/char/orb/layer3.png", "img/char/orb/layer4.png", "img/char/orb/layer5.png", "img/char/orb/layer6.png", "img/char/orb/layer1d.png", "img/char/orb/layer2d.png", "img/char/orb/layer3d.png", "img/char/orb/layer4d.png", "img/char/orb/layer5d.png" };
    private static final float[] LAYER_SPEED = new float[] { -40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F };


    //初始生命，最大生命，初始金币，能量球个数，进阶时下降的生命值
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;

    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("Ava:DefaultCharacter");

    public DefaultCharacter(String name) {
        super(name, ThmodClassEnum.DEFAULT_CHARACTER_CLASS, orbTextures, "img/char/orb/vfx.png",LAYER_SPEED, null, null);

        //对话框位置
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 220.0F * Settings.scale);

        //初始化
        initializeClass(AVA_STAND, AVA_SHOULDER_2,
                AVA_SHOULDER_1,
                AVA_CORPSE,
                getLoadout(), 20.0F, -20.0F, 205.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

    }

    /**
     * 添加初始卡组
     * @return
     */
    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("Ava:Strike_Ava");
        retVal.add("Ava:Strike_Ava");
        retVal.add("Ava:NotWell_Ava");
        retVal.add("Ava:Strike_Ava");
        retVal.add("Ava:Defend_Ava");
        retVal.add("Ava:Defend_Ava");
        retVal.add("Ava:Defend_Ava");
        retVal.add("Ava:Defend_Ava");
        return retVal;
    }

    /**
     * 添加初始遗物
     * @return
     */
    @Override
    public ArrayList<String> getStartingRelics() {
        //添加初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        return retVal;
    }

    /**
     * 选英雄界面的文字描述
     * @return
     */
    @Override
    public CharSelectInfo getLoadout() {

        String title=characterStrings.NAMES[0];
        String flavor=characterStrings.TEXT[0];

        return new CharSelectInfo(title, flavor, STARTING_HP, MAX_HP,HAND_SIZE , STARTING_GOLD, ASCENSION_MAX_HP_LOSS, this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return "向晚";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.AVA_BLUE;
    }

    @Override
    public Color getCardRenderColor() {
        return AvaMod.DEFAULT_AVA_BLUE;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return null;
    }

    @Override
    public Color getCardTrailColor() {
        return AvaMod.DEFAULT_AVA_BLUE;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return ASCENSION_MAX_HP_LOSS;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {

    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return null;
    }

    @Override
    public String getLocalizedCharacterName() {
        return "向晚";
    }

    @Override
    public AbstractPlayer newInstance() {
        return (AbstractPlayer)new DefaultCharacter(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[1];
    }

    @Override
    public Color getSlashAttackColor() {
        return AvaMod.DEFAULT_AVA_BLUE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[0];
    }

    @Override
    public String getVampireText() {
        return null;
    }
}
