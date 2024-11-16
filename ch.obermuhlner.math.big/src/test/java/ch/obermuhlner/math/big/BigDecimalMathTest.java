package ch.obermuhlner.math.big;

import ch.obermuhlner.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static ch.obermuhlner.util.ThreadUtil.runMultiThreaded;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BigDecimalMathTest extends AbstractBigDecimalTest {
	@Test
	public void testInternals() {
        assertNull(toCheck(Double.NaN));
        assertNull(toCheck(Double.NEGATIVE_INFINITY));
        assertNull(toCheck(Double.POSITIVE_INFINITY));

        assertBigDecimal(BD("1.23"), BD("1.23"), new MathContext(3));
        assertBigDecimal(BD("1.23"), BD("1.23"), new MathContext(2));
        assertBigDecimal(BD("1.23"), BD("1.23"), new MathContext(1));

        assertBigDecimal(BD("1.24"), BD("1.23"), new MathContext(3));
        assertBigDecimal(BD("1.23"), BD("1.24"), new MathContext(3));

        assertThrows(IllegalArgumentException.class, () -> { throw new IllegalArgumentException(); });
        assertThrows(IllegalArgumentException.class, "blabla", () -> { throw new IllegalArgumentException("blabla"); });
	}

    @Test(expected = java.lang.AssertionError.class)
    public void testInternalsAssertBigDecimalFail1() {
        assertBigDecimal(BD("1.25"), BD("1.23"), new MathContext(3));
    }

    @Test(expected = java.lang.AssertionError.class)
    public void testInternalsBigDecimalFail2() {
        assertBigDecimal(BD("1.23"), BD("1.25"), new MathContext(3));
    }

    @Test(expected = java.lang.AssertionError.class)
    public void testInternalsAssertThrowsFail1() {
        assertThrows(IllegalArgumentException.class, () -> { });
    }

    @Test(expected = java.lang.AssertionError.class)
    public void testInternalsAssertThrowsFail2() {
        assertThrows(IllegalArgumentException.class, () -> { throw new RuntimeException(); });
    }

    @Test(expected = java.lang.AssertionError.class)
    public void testInternalsAssertThrowsFail3() {
        assertThrows(IllegalArgumentException.class, "blabla", () -> { throw new IllegalArgumentException("another message"); });
    }

	@Test
	public void testMantissa() {
		assertEquals(0, BigDecimal.ZERO.compareTo(BigDecimalMath.mantissa(BigDecimal.ZERO)));

		assertEquals(0, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("123.45"))));
		assertEquals(0, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("0.00012345"))));

		assertEquals(0, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("123.45"))));
		assertEquals(0, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("0.00012345"))));

		assertEquals(0, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("123.45000"))));
		assertEquals(0, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("0.00012345000"))));

		assertEquals(0, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("12.345E1"))));
		assertEquals(0, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("0.0012345E-1"))));

		assertEquals(-1, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("123.459999"))));
		assertEquals(1, BD("1.2345").compareTo(BigDecimalMath.mantissa(BD("0.000123"))));
	}
	
	@Test
	public void testExponent() {
		assertEquals(0, BigDecimalMath.exponent(BigDecimal.ZERO));

		assertEquals(0, BigDecimalMath.exponent(BD("1.2345")));
		assertEquals(2, BigDecimalMath.exponent(BD("123.45")));
		assertEquals(-2, BigDecimalMath.exponent(BD("0.012345")));
		
		assertEquals(0, BigDecimalMath.exponent(BD("123.45E-2")));
		assertEquals(0, BigDecimalMath.exponent(BD("0.012345E2")));

		assertEquals(3, BigDecimalMath.exponent(BD("1.2345E3")));
		assertEquals(5, BigDecimalMath.exponent(BD("123.45E3")));
		assertEquals(1, BigDecimalMath.exponent(BD("0.012345E3")));
	}

	@Test
	public void testSignificantDigits() {
		assertEquals(1, BigDecimalMath.significantDigits(BigDecimal.ZERO));

		assertEquals(3, BigDecimalMath.significantDigits(BD("123")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("12.3")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("1.23")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("0.123")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("0.0123")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("0.00123")));

		assertEquals(3, BigDecimalMath.significantDigits(BD("12.300")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("1.2300")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("0.12300")));

		assertEquals(6, BigDecimalMath.significantDigits(BD("123000")));
		assertEquals(6, BigDecimalMath.significantDigits(BD("123000.00")));

		assertEquals(3, BigDecimalMath.significantDigits(BD("-123")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("-12.3")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("-1.23")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("-0.123")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("-0.0123")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("-0.00123")));

		assertEquals(3, BigDecimalMath.significantDigits(BD("-12.300")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("-1.2300")));
		assertEquals(3, BigDecimalMath.significantDigits(BD("-0.12300")));

		assertEquals(6, BigDecimalMath.significantDigits(BD("-123000")));
		assertEquals(6, BigDecimalMath.significantDigits(BD("-123000.00")));
	}

	@Test
	public void testIntegralPart() {
		assertEquals(0, BigDecimal.ZERO.compareTo(BigDecimalMath.integralPart(BigDecimal.ZERO)));

		assertEquals(0, BD("1").compareTo(BigDecimalMath.integralPart(BD("1.2345"))));
		assertEquals(0, BD("123").compareTo(BigDecimalMath.integralPart(BD("123.45"))));
		assertEquals(0, BD("0").compareTo(BigDecimalMath.integralPart(BD("0.12345"))));

		assertEquals(0, BD("-1").compareTo(BigDecimalMath.integralPart(BD("-1.2345"))));
		assertEquals(0, BD("-123").compareTo(BigDecimalMath.integralPart(BD("-123.45"))));
		assertEquals(0, BD("-0").compareTo(BigDecimalMath.integralPart(BD("-0.12345"))));

		assertEquals(0, BD("123E987").compareTo(BigDecimalMath.integralPart(BD("123E987"))));
		assertEquals(0, BD("0").compareTo(BigDecimalMath.integralPart(BD("123E-987"))));
	}
	
	@Test
	public void testFractionalPart() {
		assertEquals(0, BigDecimal.ZERO.compareTo(BigDecimalMath.fractionalPart(BigDecimal.ZERO)));

		assertEquals(0, BD("0.2345").compareTo(BigDecimalMath.fractionalPart(BD("1.2345"))));
		assertEquals(0, BD("0.45").compareTo(BigDecimalMath.fractionalPart(BD("123.45"))));
		assertEquals(0, BD("0.12345").compareTo(BigDecimalMath.fractionalPart(BD("0.12345"))));

		assertEquals(0, BD("-0.2345").compareTo(BigDecimalMath.fractionalPart(BD("-1.2345"))));
		assertEquals(0, BD("-0.45").compareTo(BigDecimalMath.fractionalPart(BD("-123.45"))));
		assertEquals(0, BD("-0.12345").compareTo(BigDecimalMath.fractionalPart(BD("-0.12345"))));

		assertEquals(0, BD("0").compareTo(BigDecimalMath.fractionalPart(BD("123E987"))));
		assertEquals(0, BD("123E-987").compareTo(BigDecimalMath.fractionalPart(BD("123E-987"))));
	}

	@Test
	public void testE() {
		// Result from wolframalpha.com: exp(1)
		BigDecimal expected = BD("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154249992295763514822082698951936680331825288693984964651058209392398294887933203625094431173012381970684161403970198376793206832823764648042953118023287825098194558153017567173613320698112509961818815930416903515988885193458072738667385894228792284998920868058257492796104841984443634632449684875602336248270419786232090021609902353043699418491463140934317381436405462531520961836908887070167683964243781405927145635490613031072085103837505101157477041718986106873969655212671546889570350354021234078498193343210681701210056278802351930332247450158539047304199577770935036604169973297250886876966403555707162268447162560798826517871341951246652010305921236677194325278675398558944896970964097545918569563802363701621120477427228364896134225164450781824423529486363721417402388934412479635743702637552944483379980161254922785092577825620926226483262779333865664816277251640191059004916449982893150566047258027786318641551956532442586982946959308019152987211725563475463964479101459040905862984967912874068705048958586717479854667757573205681288459205413340539220001137863009455606881667400169842055804033637953764520304024322566135278369511778838638744396625322498506549958862342818997077332761717839280349465014345588970719425863987727547109629537415211151368350627526023264847287039207643100595841166120545297030");
		assertPrecisionCalculation(
				expected,
				mathContext -> BigDecimalMath.e(mathContext),
				10);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testEUnlimitedFail() {
		BigDecimalMath.e(MathContext.UNLIMITED);
	}

	@Test
	public void testPi() {
		// Result from wolframalpha.com: pi
		BigDecimal expected = BD("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136297747713099605187072113499999983729780499510597317328160963185950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473035982534904287554687311595628638823537875937519577818577805321712268066130019278766111959092164201989380952572010654858632788659361533818279682303019520353018529689957736225994138912497217752834791315155748572424541506959508295331168617278558890750983817546374649393192550604009277016711390098488240128583616035637076601047101819429555961989467678374494482553797747268471040475346462080466842590694912933136770289891521047521620569660240580381501935112533824300355876402474964732639141992726042699227967823547816360093417216412199245863150302861829745557067498385054945885869269956909272107975093029553211653449872027559602364806654991198818347977535663698074265425278625518184175746728909777727938000816470600161452491921732172147723501414419735685481613611573525521334757418494684385233239073941433345477624168625189835694855620992192221842725502542568876717904946016534668049886272327917860857843838279679766814541009538");
		assertPrecisionCalculation(
			expected,
			mathContext -> BigDecimalMath.pi(mathContext),
			10);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testPiUnlimitedFail() {
		BigDecimalMath.pi(MathContext.UNLIMITED);
	}

	@Test
	public void testBernoulli() {
        assertBigDecimal(toCheck(1),			BigDecimalMath.bernoulli(0, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(-1.0/2),		BigDecimalMath.bernoulli(1, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(1.0/6),		BigDecimalMath.bernoulli(2, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(0),			BigDecimalMath.bernoulli(3, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(-1.0/30),	BigDecimalMath.bernoulli(4, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(0),			BigDecimalMath.bernoulli(5, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(1.0/42),		BigDecimalMath.bernoulli(6, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(0),			BigDecimalMath.bernoulli(7, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(-1.0/30),	BigDecimalMath.bernoulli(8, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(0),			BigDecimalMath.bernoulli(9, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(5.0/66),		BigDecimalMath.bernoulli(10, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(0),			BigDecimalMath.bernoulli(11, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(-691.0/2730),BigDecimalMath.bernoulli(12, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(0),			BigDecimalMath.bernoulli(13, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(7.0/6),		BigDecimalMath.bernoulli(14, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(0),			BigDecimalMath.bernoulli(15, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(-3617.0/510),BigDecimalMath.bernoulli(16, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(0),			BigDecimalMath.bernoulli(17, MC), MC_CHECK_DOUBLE);
        assertBigDecimal(toCheck(43867.0/798),BigDecimalMath.bernoulli(18, MC), MC_CHECK_DOUBLE);
	}

	@Test(expected = ArithmeticException.class)
	public void testBernoulliNegative() {
		BigDecimalMath.bernoulli(-1, MC);
	}

	@Test
	public void testBernoulliUnlimited() {
		assertBigDecimal(toCheck(1), BigDecimalMath.bernoulli(0, MathContext.UNLIMITED), MC_CHECK_DOUBLE);
		assertBigDecimal(toCheck(-1.0/2), BigDecimalMath.bernoulli(1, MathContext.UNLIMITED), MC_CHECK_DOUBLE);
		assertBigDecimal(toCheck(1), BigDecimalMath.bernoulli(3, MathContext.UNLIMITED), MC_CHECK_DOUBLE);
	}

	@Test(expected = ArithmeticException.class)
	public void testBernoulliUnlimitedFail() {
		BigDecimalMath.bernoulli(2, MathContext.UNLIMITED);
	}

	@Test
	public void testReciprocal() {
		assertEquals(BigDecimal.ONE, BigDecimalMath.reciprocal(BigDecimal.valueOf(1), MC));
		assertEquals(BigDecimal.valueOf(0.5), BigDecimalMath.reciprocal(BigDecimal.valueOf(2), MC));
	}

	@Test
	public void testReciprocalUnlimited() {
		assertEquals(BigDecimal.ONE, BigDecimalMath.reciprocal(BigDecimal.valueOf(1), MathContext.UNLIMITED));
		assertEquals(BigDecimal.valueOf(0.5), BigDecimalMath.reciprocal(BigDecimal.valueOf(2), MathContext.UNLIMITED));
	}

	@Test(expected = ArithmeticException.class)
	public void testReciprocalUnlimitedFail() {
		BigDecimalMath.reciprocal(BigDecimal.valueOf(3), MathContext.UNLIMITED);
	}

	@Test(expected = ArithmeticException.class)
	public void testReciprocalFail() {
		assertEquals(BigDecimal.valueOf(123), BigDecimalMath.reciprocal(BigDecimal.ZERO, MC));
	}

	@Test
	public void testGamma() {
		// Result from wolframalpha.com: gamma(1.5)
		BigDecimal expected = BD("0.886226925452758013649083741670572591398774728061193564106903894926455642295516090687475328369272332708113411812");
		assertPrecisionCalculation(
		   expected,
		   mathContext -> BigDecimalMath.gamma(BD("1.5"), mathContext),
		   10);
	}

	@Test
	public void testGammaSlightlyPositive() {
		// Result from wolframalpha.com: gamma(0.5)
		BigDecimal expected = BD("1.77245385090551602729816748334114518279754945612238712821380778985291128459103218137495065673854466541622682362428");
		assertPrecisionCalculation(
		   expected,
		   mathContext -> BigDecimalMath.gamma(BD("0.5"), mathContext),
		   10);
	}

	@Test
	public void testGammaNegative() {
		// Result from wolframalpha.com: gamma(-1.5)
		BigDecimal expected = BD("2.36327180120735470306422331112152691039673260816318283761841038647054837945470957516660087565139288722163576483237676");
		assertPrecisionCalculation(
		   expected,
		   mathContext -> BigDecimalMath.gamma(BD("-1.5"), mathContext),
		   10);
	}

	@Test
	public void testGammaIntegerValues() {
		assertEquals(BigDecimalMath.round(BD(1), MC), BigDecimalMath.gamma(BigDecimal.valueOf(1), MC));
		assertEquals(BigDecimalMath.round(BD(1), MC), BigDecimalMath.gamma(BigDecimal.valueOf(2), MC));
		assertEquals(BigDecimalMath.round(BD(2), MC), BigDecimalMath.gamma(BigDecimal.valueOf(3), MC));
		assertEquals(BigDecimalMath.round(BD(6), MC), BigDecimalMath.gamma(BigDecimal.valueOf(4), MC));
		assertEquals(BigDecimalMath.round(BD(24), MC), BigDecimalMath.gamma(BigDecimal.valueOf(5), MC));
		assertEquals(BigDecimalMath.round(BD(120), MC), BigDecimalMath.gamma(BigDecimal.valueOf(6), MC));
	}

	@Test
	public void testExp() {
		for(double value : new double[] { -5, -1, 0.1, 2, 10 }) {
			assertBigDecimal("exp(" + value + ")",
					toCheck(Math.exp(value)),
					BigDecimalMath.exp(BigDecimal.valueOf(value), MC),
                    MC_CHECK_DOUBLE);
		}
	}

	@Test
	public void testExpHuge() {
		// Largest exp(10^x) that still gives a result on Wolfram Alpha 
		// exp(1000000000) = 8.00298177066097253304190937436500068878231499717... * 10^434294481
		BigDecimal expected = BD("8.00298177066E434294481");
		BigDecimal actual = BigDecimalMath.exp(BigDecimal.valueOf(1000000000), new MathContext(12));
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExp1E() {
		for (int precision = 1; precision <= 2001; precision+=100) {
			MathContext mathContext = new MathContext(precision);
			assertEquals("exp(1)",
					toCheck(BigDecimalMath.e(mathContext)),
					toCheck(BigDecimalMath.exp(BigDecimal.ONE, mathContext)));
		}
	}

	@Test
	public void testExpHighAccuracy1() {
		// Result from wolframalpha.com: exp(0.1)
		BigDecimal expected = BigDecimalMath.toBigDecimal("1.1051709180756476248117078264902466682245471947375187187928632894409679667476543029891433189707486536329171204854012445361537347145315787020068902997574505197515004866018321613310249357028047934586850494525645057122112661163770326284627042965573236001851138977093600284769443372730658853053002811154007820888910705403712481387499832879763074670691187054786420033729321209162792986139109713136202181843612999064371057442214441509033603625128922139492683515203569550353743656144372757405378395318324008280741587539066613515113982139135726893022699091000215648706791206777090283207508625041582515035160384730085864811589785637025471895631826720701700554046867490844416060621933317666818019314469778173494549497985045303406629427511807573756398858555866448811811806333247210364950515781422279735945226411105718464916466588898895425154437563356326922423993425668055030150187978568089290481077628854935380963680803086975643392286380110893491216896970405186147072881173903395370306903756052863966751655566156177044091023716763999613715961429909147602055822171056918247483370329310652377494326018131931115202583455695740577117305727325929270892586003078380276849851024733440526333630939768046873818746897979176031710638428538365444373036344477660068827517905394205724765809719068497652979331103372768988364106139063845834332444587680278142035133567220351279735997089196132184270510670193246409032174006524564495804123904224547124821906736781803247534842994079537510834190198353331683651574603364551464993636940684957076677363104098202444018343049556576017452467191522001230198866508508728780804296630956390659819928014152407848066718063601429519635764058390569704470217925967541099757148635387989599481795155282833193600584112822014656645896726556449347326910544815360769564296952628696236865028848565540573895707695598984577773238");
		assertPrecisionCalculation(
				expected,
				mathContext -> BigDecimalMath.exp(BD("0.1"), mathContext),
				10);
	}

	@Test
	public void testExpHighAccuracy2() {
		// Result from wolframalpha.com: exp(123.4)
		BigDecimal expected = BigDecimalMath.toBigDecimal("390786063200889154948155379410925404241701821048363382.932350954191939407875540538095053725850542917235991826991631549658381619846119064767940229694652504799690942074237719293556052198585602941442651814977379463173507703540164446248233994372649675083170661574855926134163163649067886904058135980414181563116455815478263535970747684634869846370078756117785925810367190913580101129012440848783613501818345221727921636036313301394206941005430607708535856550269918711349535144151578877168501672228271098301349906118292542981905746359989070403424693487891904874342086983801039403550584241691952868285098443443717286891245248589074794703961309335661643261592184482383775612097087066220605742406426487994296854782150419816494210555905079335674950579368212272414401633950719948812364415716009625682245889528799300726848267101515893741151833582331196187096157250772884710690932741239776061706938673734755604112474396879294621933875319320351790004373826158307559047749814106033538586272336832756712454484917457975827690460377128324949811226379546825509424852035625713328557508831082726245169380827972015037825516930075858966627188583168270036404466677106038985975116257215788600328710224325128935656214272530307376436037654248341541925033083450953659434992320670198187236379508778799056681080864264023524043718014105080505710276107680142887912693096434707224622405921182458722451547247803222237498053677146957211048297712875730899381841541047254172958887430559055751735318481711132216206915942752379320012433097749980476094039036829992786175018479140791048841069099146681433638254527364565199203683980587269493176948487694117499339581660653106481583097500412636413209554147009042448657752082659511080673300924304690964484273924800648584285968546527296722686071417123776801220060226116144242129928933422759721847194902947144831258");
		assertPrecisionCalculation(
				expected,
				mathContext -> BigDecimalMath.exp(BD("123.4"), mathContext),
				60);
	}

	@Test
	public void testExpRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"exp",
				random -> random.nextDouble() * 100 - 50,
				Math::exp,
				(x, mathContext) -> BigDecimalMath.exp(x, mathContext));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testExpUnlimitedFail() {
		BigDecimalMath.exp(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testSin() {
		for(double value : new double[] { -10, -5, -1, -0.3, 0, 0.1, 2, 10, 20, 222 }) {
			assertBigDecimal("sin(" + value + ")",
					toCheck(Math.sin(value)),
					BigDecimalMath.sin(BigDecimal.valueOf(value), MC),
                    MC_CHECK_DOUBLE);
		}
	}

	@Test
	public void testSinHighAccuracy() {
		// Result from wolframalpha.com: sin(1.234)
		BigDecimal expected = BigDecimalMath.toBigDecimal("0.9438182093746337048617510061568275895172142720765760747220911781871399906968099483012598865055627858443507995518738766093869470509555068501582327052306784505752678705592908705201008148688700290760777223780263846758767378849305659165171458418076473553139600704400668632728702595059340199442411041490960324146869032516728992265808389968786198384238945833333329583982909393226122072922972072082343881982280834707504367506003311264818344731205557095928837491316071651630909050078777342482603092413467227932481298625668189293277970973821823536368859836352290171029827678389361668326651223313262181049179177713541062354198699357532113523026870736528786100665809233401695953717292150408826019906221690064294418649612406003915087946369501457359604343584263199153607653049282756925573849745513783165941970858623580447565222079996405576286670288022685431434886874295950242554364666123772837748084818582410730641892357161908769689946576427006541439717287833624991188137124554987468952436155712514180011917087180464841510692660163853984256220178122573051503993728719511214066957647751102014250171535662112264708511179562539851056691807479887430154563476132015884380272176766265870281843666030351481875369524292556759059067229573601315888931475939650530190997869732280644783380897437687282157862590038715019700476516674872568434184233136320198348795549241647388226943616471234865808472025746819601113742677172125085499919170003010129528504832452877371569832101275092363746925641703736428733071588960741542241552270894271703880793621738884941850045978201484407786879714905305225922874339567944723190660416232538921600185338494145628390029969393239498992087392435528382285271962107670662847438424222622822172719234821254495443425396088216409484488852445333426778397941937246299790022429378799080231482904310254381416336471042617299708975");
		assertPrecisionCalculation(
				expected,
				mathContext -> BigDecimalMath.sin(BD("1.234"), mathContext),
				10);
	}

	@Test
	public void testSinRandom() {
		testSinRandom(100);
	}
	public void testSinRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"sin",
				random -> random.nextDouble() * 100 - 50,
				Math::sin,
				(x, mathContext) -> BigDecimalMath.sin(x, mathContext));
	}

	@Test
	public void testSinRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testSinRandom(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSinUnlimitedFail() {
		BigDecimalMath.sin(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testAsin() {
		for(double value : new double[] { -1, -0.999, -0.9, -0.1, 0, 0.1, 0.9, 0.999, 1.0 }) {
			assertBigDecimal("asin(" + value + ")",
					toCheck(Math.asin(value)),
					BigDecimalMath.asin(BigDecimal.valueOf(value), MC),
                    MC_CHECK_DOUBLE);
		}
	}

	@Test
	public void testAsinRandom() {
		testAsinRandom(100);
	}

	public void testAsinRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"asin",
				random -> random.nextDouble() * 2 - 1,
				Math::asin,
				(x, mathContext) -> BigDecimalMath.asin(x, mathContext));
	}

	@Test
	public void testAsinRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testAsinRandom(10));
	}


	@Test(expected = ArithmeticException.class)
	public void testAsinGreaterOne() {
		BigDecimalMath.asin(BD("1.00001"), MC);
	}

	@Test(expected = ArithmeticException.class)
	public void testAsinSmallerMinusOne() {
		BigDecimalMath.asin(BD("-1.00001"), MC);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAsinUnlimitedFail() {
		BigDecimalMath.asin(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testCos() {
		for(double value : new double[] { -5, -1, -0.3, 0, 0.1, 2, 10 }) {
			assertBigDecimal("cos(" + value + ")",
					toCheck(Math.cos(value)),
					BigDecimalMath.cos(BigDecimal.valueOf(value), MC),
                    MC_CHECK_DOUBLE);
		}
	}

	@Test
	public void testCosHighAccuracy() {
		// Result from wolframalpha.com: cos(1.234)
		BigDecimal expected = BigDecimalMath.toBigDecimal("0.3304651080717298574032807727899271437216920101969540348605304415152510377850481654650247150200780863415535299490857568279769354541967379397431278152484662377393883768410533419456683721348368071060447119629226464127475191769149818089642412919990646712138828462407239787011203786844401859479654861215480468553428321928822608813865008312100125205763217809424012405019490461648738007730900576327363563072819683608077467442286094847912950576189413624713414163958384339772584148744200648200688260933678578647517949013249027860144759454924413798901254668352778102301380649346953594529136819938821616590614874123930824463095104424946532966863750206459438812141713997562660701774968530149079881716322567945593156313333714539747617833144412172753445042952390635799639722239182963246046253903297563427741240081854182759746064810195237864060495745282046388159544259160022883886283097655348787382625328541498058884531961700370121969709480517496749271767735566816249479148488140162802977360971480510530896749944967304972380342831111213248738743617588927820627474733980422901948506009170945896565358929343777077336070289567245971065005860921723126096986632224093068775586235017140132374230378564807873973345322857782900999655081761884197357196908109838154083921138904571471346009606070648486103795109388774364448499820533743041120697352743044140279966823607345221684081898024173036376672034911709557102798619864101440725109041264516550229345850413762376113868869256025801898710854538411051622029568572639882301754336762028948110406127835411158515890274188501674397646117070538768699719967119559314804437052735458481025364866752041137855637961697664203246176781407193905595472755222134533679020285886126388322265972029035063590381025908006103799793443322205833892605275969980406879438015951448721792889383476504454337544038606643477976186");
		assertPrecisionCalculation(
		   expected,
		   mathContext -> BigDecimalMath.cos(BD("1.234"), mathContext),
		   10);
	}

	@Test
	public void testCosRandom() {
		testCoshRandom(100);
	}

	public void testCosRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"cos",
				random -> random.nextDouble() * 100 - 50,
				Math::cos,
				(x, mathContext) -> BigDecimalMath.cos(x, mathContext));
	}

	@Test
	public void testCosRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testCosRandom(10));
	}


	@Test(expected = UnsupportedOperationException.class)
	public void testCosUnlimitedFail() {
		BigDecimalMath.cos(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testAcosRandom() {
		testAcosRandom(100);
	}

	public void testAcosRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"acos",
				random -> random.nextDouble() * 2 - 1,
				Math::acos,
				(x, mathContext) -> BigDecimalMath.acos(x, mathContext));
	}

	@Test
	public void testAcosRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testAcosRandom(10));
	}

	@Test
	public void testAcosMinusOne() {
		for (int precision = 1; precision <= 2001; precision+=100) {
			MathContext mathContext = new MathContext(precision);
			BigDecimal pi = BigDecimalMath.pi(mathContext);
			BigDecimal acosMinusOne = BigDecimalMath.acos(BigDecimal.ONE.negate(), mathContext);
			assertEquals(true, pi.compareTo(acosMinusOne) == 0);
		}
	}
	
	@Test(expected = ArithmeticException.class)
	public void testAcosGreaterOne() {
		BigDecimalMath.acos(BD("1.00001"), MC);
	}

	@Test(expected = ArithmeticException.class)
	public void testAcosSmallerMinusOne() {
		BigDecimalMath.acos(BD("-1.00001"), MC);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAcosUnlimitedFail() {
		BigDecimalMath.acos(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testAcoshZero() {
		Exception actualException1 = assertThrows(ArithmeticException.class, () ->
				BigDecimalMath.acosh(BigDecimal.ZERO, MathContext.DECIMAL128)
		);
		assertEquals("Illegal acosh(x) for x >= 1: x = 0", actualException1.getMessage());

		Exception actualException2 = assertThrows(ArithmeticException.class, () ->
				BigDecimalMath.acosh(BD("0.99999"), MathContext.DECIMAL128)
		);
		assertEquals("Illegal acosh(x) for x >= 1: x = 0.99999", actualException2.getMessage());
	}

	@Test
	public void testTan() {
		for(double value : new double[] { 1.1, -10, -5, -1, -0.3, 0, 0.1, 2, 10, 20, 222 }) {
			assertBigDecimal("tan(" + value + ")",
					toCheck(Math.tan(value)),
					BigDecimalMath.tan(BigDecimal.valueOf(value), MC),
                    MC_CHECK_DOUBLE);
		}
	}

	@Test
	public void testTanRandom() {
		testTanRandom(100);
	}

	public void testTanRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"tan",
				random -> random.nextDouble() * 100 - 50,
				Math::tan,
				(x, mathContext) -> BigDecimalMath.tan(x, mathContext));
	}

	@Test
	public void testTanRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testTanRandom(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testTanUnlimitedFail() {
		BigDecimalMath.tan(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testAtanRandom() {
		testAtanRandom(100);
	}

	public void testAtanRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"atan",
				random -> random.nextDouble() * 100 - 50,
				Math::atan,
				(x, mathContext) -> BigDecimalMath.atan(x, mathContext));
	}

	@Test
	public void testAtanRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testAtanRandom(10));
	}

	@Test(expected = ArithmeticException.class)
	public void testAtan2ZeroZero() {
		BigDecimalMath.atan2(BigDecimal.ZERO, BigDecimal.ZERO, MC);
	}
	
	@Test
	public void testAtan2() {
		BigDecimal pi = BigDecimalMath.pi(MC);
		BigDecimal piHalf = BigDecimalMath.pi(new MathContext(MC.getPrecision() + 10)).divide(BigDecimal.valueOf(2), MC);

		assertEquals(piHalf, BigDecimalMath.atan2(BigDecimal.TEN, BigDecimal.ZERO, MC));
		assertEquals(piHalf.negate(), BigDecimalMath.atan2(BigDecimal.TEN.negate(), BigDecimal.ZERO, MC));
		assertEquals(pi, BigDecimalMath.atan2(BigDecimal.ZERO, BigDecimal.TEN.negate(), MC));
	}

	@Test
	public void testAtan2HighAccuracy() {
		// Result from wolframalpha.com: atan2(123456789, 987654321)
		BigDecimal expected = BigDecimalMath.toBigDecimal("0.12435499342522297334968147683476071896899844881294839643180323485370657121551589550118807775010954424614161504017100766102274760347773144234717264025098792427062054083201522193127589446759654134052154287720616792464947608894974472784377275294647356650781819500878799427824049369273085965502705708393722924586235499530689424922197361614748436463923316792256542944548723768578591781632376382102987565485177288344132765819127765508975729310469873258293578995");
		assertPrecisionCalculation(
		   expected,
		   mathContext -> BigDecimalMath.atan2(BD("123456789"), BD("987654321"), mathContext),
		   10);
	}

	@Test
	public void testAtan2Random() {
		testAtan2Random(100);
	}

	public void testAtan2Random(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"atan2",
				random -> random.nextDouble() * 100 - 50,
				random -> random.nextDouble() * 100 - 50,
				Math::atan2,
		   		(y, x, mathContext) -> {
					BigDecimal pi = BigDecimalMath.pi(mathContext);
					BigDecimal result = BigDecimalMath.atan2(y, x, mathContext);
					if (result.compareTo(pi.negate()) < 0 || result.compareTo(pi) > 0) {
						BigDecimalMath.atan2(y, x, mathContext);
					   fail("outside allowed range: " + result + " for y=" + y + ", x=" + x);
					}
					return result;
			   	});
	}

	@Test
	public void testAtan2RandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testAtan2Random(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAtanUnlimitedFail() {
		BigDecimalMath.atan(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testCot() {
		for(double value : new double[] { 0.5, -0.5 }) {
			assertBigDecimal("cot(" + value + ")",
					toCheck(cot(value)),
					BigDecimalMath.cot(BigDecimal.valueOf(value), MC),
                    MC_CHECK_DOUBLE);
		}
	}

	private double cot(double x) {
		return Math.cos(x) / Math.sin(x);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testCotUnlimitedFail() {
		BigDecimalMath.cot(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testSinhRandom() {
		testSinhRandom(100);
	}

	public void testSinhRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"sinh",
				random -> random.nextDouble() * 100 - 50,
				Math::sinh,
				(x, mathContext) -> BigDecimalMath.sinh(x, mathContext));
	}

	@Test
	public void testSinhRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testSinhRandom(10));
	}

	@Test
	public void testAsinhRandom() {
		testAsinhRandom(100);
	}

	public void testAsinhRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"asinh",
				random -> random.nextDouble() * 100 - 50,
				BigDecimalMathTest::asinh,
				(x, mathContext) -> BigDecimalMath.asinh(x, mathContext));
	}

	@Test
	public void testAsinhRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testAsinhRandom(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAsinhUnlimitedFail() {
		BigDecimalMath.asinh(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	public static double asinh(double x) {
		return Math.log(x + Math.sqrt(x*x + 1));
	}
	
	@Test
	public void testAcoshRandom() {
		testAcoshRandom(100);
	}

	public void testAcoshRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"acosh",
				random -> random.nextDouble() * 100 + 1,
				BigDecimalMathTest::acosh,
				(x, mathContext) -> BigDecimalMath.acosh(x, mathContext));
	}

	@Test
	public void testAcoshRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testAcoshRandom(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAcoshUnlimitedFail() {
		BigDecimalMath.acosh(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	public static double acosh(double x) {
		return Math.log(x + Math.sqrt(x*x - 1));
	}
	
	@Test
	public void testAtanhRandom() {
		testAtanhRandom(100);
	}

	public void testAtanhRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"atanh",
				random -> random.nextDouble() * 1.9999 - 1,
				BigDecimalMathTest::atanh,
				(x, mathContext) -> BigDecimalMath.atanh(x, mathContext));
	}

	@Test
	public void testAtanhRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testAtanhRandom(10));
	}

    @Test(expected = ArithmeticException.class)
    public void testAtanhFailOne() {
        BigDecimalMath.atanh(BigDecimal.ONE, MC);
    }

    @Test(expected = ArithmeticException.class)
    public void testAtanhFailMinusOne() {
        BigDecimalMath.atanh(BigDecimal.ONE.negate(), MC);
    }

    @Test(expected = UnsupportedOperationException.class)
	public void testAtanhUnlimitedFail() {
		BigDecimalMath.atanh(BigDecimal.valueOf(0), MathContext.UNLIMITED);
	}

	public static double atanh(double x) {
		return Math.log((1+x)/(1-x))/2;
	}
	
	@Test
	public void testAcothRandom() {
		testAcothRandom(100);
	}

	public void testAcothRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"acoth",
				random -> random.nextDouble() * 100 + 1,
				BigDecimalMathTest::acoth,
				(x, mathContext) -> BigDecimalMath.acoth(x, mathContext));

		assertRandomCalculation(
				adaptCount(count),
				"acoth",
				random -> -(random.nextDouble() * 100 + 1),
				BigDecimalMathTest::acoth,
				(x, mathContext) -> BigDecimalMath.acoth(x, mathContext));
	}

	@Test
	public void testAcothRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testAcothRandom(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testAcothUnlimitedFail() {
		BigDecimalMath.acoth(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	public static double acoth(double x) {
		return Math.log((x+1)/(x-1))/2;
	}
	
	@Test
	public void testCoshRandom() {
		testCoshRandom(1000);
	}

	public void testCoshRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"cosh",
				random -> random.nextDouble() * 100 - 50,
				Math::cosh,
				(x, mathContext) -> BigDecimalMath.cosh(x, mathContext));
	}

	@Test
	public void testCoshRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testCoshRandom(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testCoshUnlimitedFail() {
		BigDecimalMath.cosh(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testTanhRandom() {
		testTanhRandom(100);
	}

	public void testTanhRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"tanh",
				random -> random.nextDouble() * 100 - 50,
				Math::tanh,
				(x, mathContext) -> BigDecimalMath.tanh(x, mathContext));
	}

	@Test
	public void testTanhRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testTanhRandom(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testTanhUnlimitedFail() {
		BigDecimalMath.tanh(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	@Test
	public void testCothHighAccuracy() {
		// Result from wolframalpha.com: coth(1.234)
		BigDecimal expected = BigDecimalMath.toBigDecimal("1.185205324770926556048860223430792270329901540411285137873448359282652953527094889287726814715510048521019659319860676563297837519357735755222676888688329329110303974145134088876752919477109732168023596610148428505480869241099661164811509156002985143871723093089577983606565248689782796839398517745077805743517260787039640386735301278276086876372931112481082107564483405956328739694693730574938024717490194723853249114925227131778575014948071122840812952034872863104176945075471984392801178291333351353562325025835067985454487645827393165057275970686744920047997309198618696444874486121281651600092381786769980527201043162650354454070144174248097841732569004869651674629227052611951971499852906803162445370338547925467890388150946743902496473437710179269365962240697297233777642354604121931412626467105497562707506260133068364009228804583415867970900958121367348411835937666408175088231826400822030426266552");
		assertPrecisionCalculation(
				expected,
				mathContext -> BigDecimalMath.coth(BD("1.234"), mathContext),
				10);
	}

	@Test
	public void testCothRandom() {
		testCothRandom(100);
	}

	public void testCothRandom(int count) {
		assertRandomCalculation(
                adaptCount(count),
				"tanh",
				random -> random.nextDouble() * 100 - 50,
				BigDecimalMathTest::coth,
				(x, mathContext) -> BigDecimalMath.coth(x, mathContext));
	}

	@Test
	public void testCothRandomMultiThreaded() throws Throwable {
		runMultiThreaded(() -> testCothRandom(10));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testCothUnlimitedFail() {
		BigDecimalMath.coth(BigDecimal.valueOf(2), MathContext.UNLIMITED);
	}

	private static double coth(double x) {
		return Math.cosh(x) / Math.sinh(x);
	}

	@Test
	public void testSinAsinRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"asin(sin(x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.asin(BigDecimalMath.sin(x, mathContext), mathContext));
	}

	@Test
	public void testCosAcosRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"acos(cos(x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.acos(BigDecimalMath.cos(x, mathContext), mathContext));
	}

	@Test
	public void testTanAtanRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"atan(tan(x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.atan(BigDecimalMath.tan(x, mathContext), mathContext));
	}

	@Test
	public void testCotAcotRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"acot(cot(x))",
				(random, mathContext) -> {
					BigDecimal r;
					do {
						r = randomBigDecimal(random, mathContext);
					} while(r.compareTo(BigDecimal.ZERO) == 0);
					return r;
				},
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.acot(BigDecimalMath.cot(x, mathContext), mathContext));
	}

	@Test(expected = ArithmeticException.class)
	public void testCotEqualZero() {
		BigDecimalMath.cot(BigDecimal.ZERO, MC);
	}

	@Test
	public void testSinhAsinhRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"asinh(sinh(x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.asinh(BigDecimalMath.sinh(x, mathContext), mathContext));
	}

	@Test
	public void testCoshAcoshRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"acosh(cosh(x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.acosh(BigDecimalMath.cosh(x, mathContext), mathContext));
	}

	@Test
	public void testTanhAtanhRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"atan(tan(x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.atanh(BigDecimalMath.tanh(x, mathContext), mathContext));
	}

	@Test
	public void testCothAcothRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"acoth(coth(x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext).add(BigDecimal.valueOf(0.0000001)),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.acoth(BigDecimalMath.coth(x, mathContext), mathContext));
	}

	@Test
	public void testLogExpRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"log(exp(x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.log(BigDecimalMath.exp(x, mathContext), mathContext));
	}

	@Test
	public void testLog10PowRandom() {
		assertRandomCalculation(
                adaptCount(1000),
				"x",
				"log10(pow(10,x))",
				(random, mathContext) -> randomBigDecimal(random, mathContext),
				(x, mathContext) -> x,
				(x, mathContext) -> BigDecimalMath.log10(BigDecimalMath.pow(BigDecimal.TEN, x, mathContext), mathContext));
	}

	@Test
	public void testToDegrees() {
		assertEquals(0, BigDecimal.ZERO.compareTo(BigDecimalMath.toDegrees(BigDecimal.ZERO, MC)));

		assertEquals(0, BD("85.94366926962348131519723222115776").compareTo(BigDecimalMath.toDegrees(BD("1.5"), MC)));
		assertEquals(0, BD("-85.94366926962348131519723222115776").compareTo(BigDecimalMath.toDegrees(BD("-1.5"), MC)));
	}

	@Test
	public void testToRadians() {
		assertEquals(0, BigDecimal.ZERO.compareTo(BigDecimalMath.toRadians(BigDecimal.ZERO, MC)));

		assertEquals(0, BD("1.570796326794896619231321691639751").compareTo(BigDecimalMath.toRadians(BD("90"), MC)));
		assertEquals(0, BD("-1.570796326794896619231321691639751").compareTo(BigDecimalMath.toRadians(BD("-90"), MC)));
	}
}
