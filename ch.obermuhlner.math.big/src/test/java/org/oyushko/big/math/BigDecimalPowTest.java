package org.oyushko.big.math;

import org.oyushko.big.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class BigDecimalPowTest extends AbstractBigDecimalTest {
    @Test(expected = ArithmeticException.class)
    public void testPowIntZeroPowerNegative() {
        BigDecimalMath.pow(BigDecimal.valueOf(0), -5, MC);
    }

    @Test
    public void testPowIntPositiveY() {
        // positive exponents
        for(int x : new int[] { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 }) {
            for(int y : new int[] { 0, 1, 2, 3, 4, 5 }) {
                assertEquals(
                        x + "^" + y,
                        BigDecimalMath.round(BigDecimal.valueOf((int) Math.pow(x, y)), MC),
                        BigDecimalMath.pow(BigDecimal.valueOf(x), y, MC));
            }
        }
    }

    @Test
    public void testPowIntUnlimited() {
        assertEquals(BigDecimal.valueOf(1.44), BigDecimalMath.pow(BigDecimal.valueOf(1.2), 2, MathContext.UNLIMITED));
        assertEquals(BigDecimal.valueOf(0.25), BigDecimalMath.pow(BigDecimal.valueOf(2), -2, MathContext.UNLIMITED));
    }

    @Test
    public void testPowIntUnnecessary() {
        MathContext mathContext = new MathContext(20, RoundingMode.UNNECESSARY);
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(2.0736), mathContext), BigDecimalMath.pow(BigDecimal.valueOf(1.2), 4, mathContext));
    }

    @Test(expected = ArithmeticException.class)
    public void testPowIntUnnecessaryFail() {
        BigDecimalMath.pow(BigDecimal.valueOf(1.2), 4, new MathContext(3, RoundingMode.UNNECESSARY));
    }

    @Test(expected = ArithmeticException.class)
    public void testPowIntUnlimitedFail() {
        BigDecimalMath.pow(BigDecimal.valueOf(1.2), -2, MathContext.UNLIMITED);
    }

    @Test
    public void testPowIntHighAccuracy() {
        // Result from wolframalpha.com: 1.000000000000001 ^ 1234567
        BigDecimal expected = BigDecimalMath.toBigDecimal("1.0000000012345670007620772217746112884011264566574371750661936042203432730421791357400340579375261062151425984605455718643834831212687809215508627027381366482513893346638309647254328483125554030430209837119592796226273439855097892690164822394282109582106572606688508863981956571098445811521589634730079294115917257238821829137340388818182807197358582081813107978164190701238742379894183398009280170118101371420721038965387736053980576803168658232943601622524279972909569009054951992769572674935063940581972099846878996147233580891866876374475623810230198932136306920161303356757346458080393981632574418878114647836311205301451612892591304592483387202671500569971713254903439669992702668656944996771767101889159835990797016804271347502053715595561455746434955874842970156476866700704289785119355240166844949092583102028847019848438487206052262820785557574627974128427022802453099783875466674774383283633178630613523399806185908766812896743349684394795523513553454443796268439405430281375480024234032172402840564635266057234920659063946839453576870882295214918516855889289061559150620879201634277096704728220897344041618549870872138380388841708643468696894694958739051584506837702527545643699395947205334800543370866515060967536750156194684592206567524739086165295878406662557303580256110172236670067327095217480071365601062314705686844139397480994156197621687313833641789783258629317024951883457084359977886729599488232112988200551717307628303748345907910029990065217835915703110440740246602046742181454674636608252499671425052811702208797030657332754492225689850123854291480472732132658657813229027494239083970478001231283002517914471878332200542180147054941938310139493813524503325181756491235593304058711999763930240249546122995086505989026270701355781888675020326791938289147344430814703304780863155994800418441632244536");
        assertPrecisionCalculation(
                expected,
                mathContext -> BigDecimalMath.pow(BD("1.000000000000001"), 1234567, mathContext),
                10);
    }

    @Test
    public void testPowIntNegativeY() {
        // positive exponents
        for(int x : new int[] { -5, -4, -3, -2, -1, 1, 2, 3, 4, 5 }) { // no x=0 !
            for(int y : new int[] { -5, -4, -3, -2, -1}) {
                assertEquals(
                        x + "^" + y,
                        BigDecimalMath.round(BigDecimal.ONE.divide(BigDecimal.valueOf((int) Math.pow(x, -y)), MC), MC),
                        BigDecimalMath.pow(BigDecimal.valueOf(x), y, MC));
            }
        }
    }

    @Test
    public void testPowIntSpecialCases() {
        // 0^0 = 1
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(1), MC), BigDecimalMath.pow(BigDecimal.valueOf(0), 0, MC));
        // 0^x = 0 for x > 0
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(0), MC), BigDecimalMath.pow(BigDecimal.valueOf(0), +5, MC));

        // x^0 = 1 for all x
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(1), MC), BigDecimalMath.pow(BigDecimal.valueOf(-5), 0, MC));
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(1), MC), BigDecimalMath.pow(BigDecimal.valueOf(+5), 0, MC));
    }

    @Test(expected = ArithmeticException.class)
    public void testPowInt0NegativeY() {
        // 0^x for x < 0 is undefined
        System.out.println(BigDecimalMath.pow(BigDecimal.valueOf(0), -5, MC));
    }

    @Test
    public void testPowPositiveX() {
        for(double x : new double[] { 1, 1.5, 2, 2.5, 3, 4, 5 }) {
            for(double y : new double[] { -5, -4, -3, -2.5, -2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2, 2.5, 3, 4, 5 }) {
                assertBigDecimal(
                        x + "^" + y,
                        toCheck(Math.pow(x, y)),
                        BigDecimalMath.pow(BigDecimal.valueOf(x), BigDecimal.valueOf(y), MC),
                        MC_CHECK_DOUBLE);
            }
        }
        for(double x : new double[] { 0 }) {
            for(double y : new double[] { 0, 0.5, 1, 1.5, 2, 2.5, 3, 4, 5 }) {
                assertBigDecimal(
                        x + "^" + y,
                        toCheck(Math.pow(x, y)),
                        BigDecimalMath.pow(BigDecimal.valueOf(x), BigDecimal.valueOf(y), MC),
                        MC_CHECK_DOUBLE);
            }
        }
    }

    @Test
    public void testPowNegativeX() {
        for(double x : new double[] { -2, -1 }) {
            for(double y : new double[] { -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5 }) {
                assertBigDecimal(
                        x + "^" + y,
                        toCheck(Math.pow(x, y)),
                        BigDecimalMath.pow(BigDecimal.valueOf(x), BigDecimal.valueOf(y), MC),
                        MC);
            }
        }
    }

    @Test
    public void testPowSpecialCases() {
        // 0^0 = 1
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(1), MC), BigDecimalMath.pow(BigDecimal.valueOf(0), BigDecimal.valueOf(0), MC));
        // 0^x = 0 for x > 0
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(0), MC), BigDecimalMath.pow(BigDecimal.valueOf(0), BigDecimal.valueOf(+5), MC));

        // x^0 = 1 for all x
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(1), MC), BigDecimalMath.pow(BigDecimal.valueOf(-5), BigDecimal.valueOf(0), MC));
        assertEquals(BigDecimalMath.round(BigDecimal.valueOf(1), MC), BigDecimalMath.pow(BigDecimal.valueOf(+5), BigDecimal.valueOf(0), MC));
    }

    @Test(expected = ArithmeticException.class)
    public void testPow0NegativeY() {
        // 0^x for x < 0 is undefined
        System.out.println(BigDecimalMath.pow(BigDecimal.valueOf(0), BigDecimal.valueOf(-5), MC));
    }

    @Test
    public void testPowHighAccuracy1() {
        // Result from wolframalpha.com: 0.12345 ^ 0.54321
        BigDecimal expected = BigDecimalMath.toBigDecimal("0.3209880595151945185125730942395290036641685516401211365668021036227236806558712414817507777010529315619538091221044550517779379562785777203521073317310721887789752732383195992338046561142233197839101366627988301068817528932856364705673996626318789438689474137773276533959617159796843289130492749319006030362443626367021658149242426847020379714749221060925227256780407031977051743109767225075035162749746755475404882675969237304723283707838724317900591364308593663647305926456586738661094577874745954912201392504732008960366344473904725152289010662196139662871362863747003357119301290791005303042638323919552042428899542474653695157843324029537490471818904797202183382709740019779991866183409872343305557416160635632389025962773383948534706993646814493361946320537133866646649868386696744314086907873844459873522561100570574729858449637845765912377361924716997579241434414109143219005616107946583880474580592369219885446517321145488945984700859989002667482906803702408431898991426975130215742273501237614632961770832706470833822137675136844301417148974010849402947454745491575337007331634736828408418815679059906104486027992986268232803807301917429934411578887225359031451001114134791114208050651494053415141140416237540583107162910153240598400275170478935634433997238593229553374738812677055332589568742194164880936765282391919077003882108791507606561409745897362292129423109172883116578263204383034775181118065757584408324046421493189442843977781400819942671602106042861790274528866034496106158048150133736995335643971391805690440083096190217018526827375909068556103532422317360304116327640562774302558829111893179295765516557567645385660500282213611503701490309520842280796017787286271212920387358249026225529459857528177686345102946488625734747525296711978764741913791309106485960272693462458335037929582834061232406160");
        assertPrecisionCalculation(
                expected,
                mathContext -> BigDecimalMath.pow(BD("0.12345"), BD("0.54321"), mathContext),
                10);
    }

    @Test
    public void testPowHighAccuracy2() {
        // Result from wolframalpha.com: 1234.5 ^ 5.4321
        BigDecimal expected = BigDecimalMath.toBigDecimal("62128200273178468.6677398330313037781753494560130835832101960387223758707669665725754895879107246310011029364211118269934534848627597104718365299707675269883473866053798863560099145230081124493870576780612499275723252481988188990085485417903685910250385975275407201318962063571641788853056193956632922391172489400257505790978314596080576631215805090936935676836971091464254857748180262699112027530753684170510323511798980747639116410705861310591624568227525136728034348718513230067867653958961909807085463366698897670703033966902227226026963721428348393842605660315775615215897171041744502317760375398468093874441545987214768846585209830041286071364933140664316884545264314137705612948991849327809564207354415319908754752255701802039139765434084951567836148382259822205056903343078315714330953561297888049627241752521508353126178543435267324563502039726903979264593590549404498146175495384414213014048644769191478319546475736458067346291095970042183567796890291583916374248166579807593334209446774446615766870268699990517113368293867016985423417705611330741518898131591089047503977721006889839010831321890964560951517989774344229913647667605138595803854678957098670003929907267918591145790413480904188741307063239101475728087298405926679231349800701106750462465201862628618772432920720630962325975002703818993580555861946571650399329644600854846155487513507946368829475408071100475344884929346742632438630083062705384305478596166582416332328006339035640924818942503261178020860473649223332292597947133883640686283632593820956826840942563265332271497540069352040396588314197259366049553760360493773149812879272759032356567261509967695159889106382819692093987902453799750689562469611095996225341555322139606462193260609916132372239906927497183040765412767764999503366952191218000245749101208123555266177028678838265168229");
        assertPrecisionCalculation(
                expected,
                mathContext -> BigDecimalMath.pow(BD("1234.5"), BD("5.4321"), mathContext),
                10);
    }

    @Test
    public void testPowLargeInt() {
        BigDecimal x = BD("1.5");
        BigDecimal y = BD("1E10"); // still possible with longValueExact()
        // Result from wolframalpha.com: 1.5 ^ 1E10
        BigDecimal expected = BD("3.60422936590014149127041615892759056162677175765E1760912590");
        assertEquals(10_000_000_000L, y.longValueExact());
        assertPrecisionCalculation(
                expected,
                mathContext -> {
                    return BigDecimalMath.pow(x, y, mathContext);
                },
                20);
    }

    @Test
    public void testPowVeryLargeInt() {
        BigDecimal x = BD("1.00000000000001");
        BigDecimal y = BD("1E20"); // not possible with than longValueExact()
        // Result from wolframalpha.com: 1.00000001 ^ 1E20
        BigDecimal expected = BD("3.03321538163601059899125791999959844544825181205425E434294");
        assertPrecisionCalculation(
                expected,
                mathContext -> {
                    return BigDecimalMath.pow(x, y, mathContext);
                },
                20);
    }

    @Test
    public void testPow_whenSpecialCase_thenShouldNotThrowOverflow() {
        BigDecimal x = BD("85");
        BigDecimal y = BD("483379540.5878915618046344614959831"); // not possible with than longValueExact()
        BigDecimal expected = BD("8.191399333915731143433650968385840E+932641633");
        assertEquals(expected, BigDecimalMath.pow(x, y, MC));
    }

    @Test(expected = ArithmeticException.class)
    public void testPowOverflow() {
        BigDecimalMath.pow(BD("123"), BD("1E20"), MC);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testPowUnlimitedFail() {
        BigDecimalMath.pow(BigDecimal.valueOf(1.2), BigDecimal.valueOf(1.1), MathContext.UNLIMITED);
    }

    @Test
    public void testPowRandom() {
        assertRandomCalculation(
                adaptCount(1000),
                "pow",
                random -> random.nextDouble() * 100 + 0.000001,
                random -> random.nextDouble() * 100 - 50,
                Math::pow,
                (x, y, mathContext) -> BigDecimalMath.pow(x, y, mathContext));
    }

    @Test
    public void testPow2Random() {
        assertRandomCalculation(
                adaptCount(1000),
                "x*x",
                "pow(x,2)",
                (random, mathContext) -> randomBigDecimal(random, mathContext),
                (x, mathContext) -> x.multiply(x, mathContext),
                (x, mathContext) -> BigDecimalMath.pow(x, 2, mathContext));
    }
}
