package demoMod;



import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.interfaces.*;
import cards.Defend_Ava;
import cards.NotWell_Ava;
import cards.Strike_Ava;
import characters.DefaultCharacter;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import pathes.AbstractCardEnum;
import pathes.ThmodClassEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SpireInitializer
public class AvaMod  implements RelicGetSubscriber, PostPowerApplySubscriber, PostExhaustSubscriber, PostBattleSubscriber, PostDungeonInitializeSubscriber, EditCharactersSubscriber, PostInitializeSubscriber, EditRelicsSubscriber, EditCardsSubscriber, EditStringsSubscriber, OnCardUseSubscriber, EditKeywordsSubscriber, OnPowersModifiedSubscriber, PostDrawSubscriber, PostEnergyRechargeSubscriber{

    public static final Color DEFAULT_AVA_BLUE = CardHelper.getColor(154F,200F,226F);

    public static final String DEFAULT_BG_ATTACK_512 = "img/512/bg_attack_512.png";
    public static final String DEFAULT_BG_POWER_512 = "img/512/bg_power_512.png";
    public static final String DEFAULT_BG_SKILL_512 = "img/512/bg_skill_512.png";
    public static final String DEFAULT_SMALL_ORB = "img/orb/small_orb.png";
    public static final String DEFAULT_BG_ATTACK_1024 = "img/1024/bg_attack_1024.png";
    public static final String DEFAULT_BG_POWER_1024 = "img/1024/bg_power_1024.png";
    public static final String DEFAULT_BG_SKILL_1024 = "img/1024/bg_skill_1024.png";
    public static final String DEFAULT_LARGE_ORB = "img/orb/large_orb.png";
    public static final String DEFAULT_COST_ORB = "img/orb/cost_orb.png";

    private static final String MY_CHARACTER_BUTTON = "img/char/AvaButton.png";
    private static final String MY_CHARACTER_PORTRAIT = "img/char/AvaPortrait.jpg";

    public static final Logger logger = LogManager.getLogger(AvaMod.class.getName());

    public static ArrayList<AbstractCard> recyclecards = new ArrayList<>();

    public AvaMod()
    {
        BaseMod.subscribe((ISubscriber)this);
        logger.info("add color.");
        BaseMod.addColor(AbstractCardEnum.AVA_BLUE,DEFAULT_AVA_BLUE,DEFAULT_AVA_BLUE,DEFAULT_AVA_BLUE,DEFAULT_AVA_BLUE,DEFAULT_AVA_BLUE,DEFAULT_AVA_BLUE,DEFAULT_AVA_BLUE,DEFAULT_BG_ATTACK_512,DEFAULT_BG_SKILL_512,DEFAULT_BG_POWER_512,DEFAULT_COST_ORB,DEFAULT_BG_ATTACK_1024,DEFAULT_BG_SKILL_1024,DEFAULT_BG_POWER_1024,DEFAULT_LARGE_ORB,DEFAULT_SMALL_ORB);
        logger.info("add color done.");
    }

    public static void initialize() {
        AvaMod mod = new AvaMod();
    }

    @Override
    public void receiveEditCards() {
        logger.info("add card.");
        BaseMod.addCard(new Strike_Ava());
        BaseMod.addCard(new Defend_Ava());
        BaseMod.addCard(new NotWell_Ava());
        logger.info("add card done.");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("add character.");
        BaseMod.addCharacter((AbstractPlayer)new DefaultCharacter("向晚"), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, ThmodClassEnum.DEFAULT_CHARACTER_CLASS);
        logger.info("add character done.");
    }

    @Override
    public void receiveEditKeywords() {
        logger.info("add Keywords.");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("add Relics.");
    }

    @Override
    public void receiveEditStrings() {
        logger.info("add Strings.");
        String card= "localization/zhs/Ava-Cards-zhs.json";
        String character = "localization/zhs/Ava-Characters-zhs.json";

        String cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
        String characterStrings = Gdx.files.internal(character).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        BaseMod.loadCustomStrings(CharacterStrings.class, characterStrings);
        logger.info("add Strings done.");
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        logger.info("add CardUsed.");
    }

    @Override
    public void receivePowersModified() {
        logger.info("add PowersModified.");
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        logger.info("add PostBattle.");
    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
        logger.info("add PostDraw.");
    }

    @Override
    public void receivePostDungeonInitialize() {
        logger.info("add PostDungeonInitialize.");
    }

    @Override
    public void receivePostEnergyRecharge() {
        logger.info("add PostEnergyRecharge.");
        Iterator<AbstractCard> var1 = recyclecards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = var1.next();
            AbstractCard card = c.makeStatEquivalentCopy();
            AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, false, true, true));
        }
        recyclecards.clear();
    }

    @Override
    public void receivePostExhaust(AbstractCard abstractCard) {
        logger.info("add PostExhaust.");
    }

    @Override
    public void receivePostInitialize() {
        HashMap<String,Sfx> reflectedMap = getSoundsMap();
        reflectedMap.put("NotWell_Ava",new Sfx("sound/NotWell.wav"));
        reflectedMap.put("Strike_Ava",new Sfx("sound/Strike.wav"));
        reflectedMap.put("Defend_Ava",new Sfx("sound/Defend.wav"));

    }
    private HashMap<String, Sfx> getSoundsMap() {
        return ReflectionHacks.getPrivate(CardCrawlGame.sound, SoundMaster.class, "map");
    }

    @Override
    public void receivePostPowerApplySubscriber(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        logger.info("add PostPowerApplySubscriber.");
    }

    @Override
    public void receiveRelicGet(AbstractRelic abstractRelic) {
        logger.info("add RelicGet.");
    }
}
