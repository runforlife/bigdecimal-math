package io.github.runforlife.bigdecimal.math;

import io.github.runforlife.bigdecimal.math.util.AbstractBigDecimalTest;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.Assert.assertEquals;

public class BigDecimalMathSqrtTest extends AbstractBigDecimalTest {
    @Test
    public void testSqrt() {
        for(double value : new double[] { 0, 0.1, 2, 4, 10, 16, 33.3333 }) {
            assertBigDecimal(
                    "sqrt(" + value + ")",
                    toCheck(Math.sqrt(value)),
                    BigDecimalMath.sqrt(BigDecimal.valueOf(value), MC),
                    MC_CHECK_DOUBLE);
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testSqrtUnlimitedFail() {
        BigDecimalMath.sqrt(BigDecimal.valueOf(2), MathContext.UNLIMITED);
    }

    @Test
    public void testSqrtHighAccuracy() {
        // Result from wolframalpha.com: sqrt(2)
        BigDecimal expected = BigDecimalMath.toBigDecimal("1.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727350138462309122970249248360558507372126441214970999358314132226659275055927557999505011527820605714701095599716059702745345968620147285174186408891986095523292304843087143214508397626036279952514079896872533965463318088296406206152583523950547457502877599617298355752203375318570113543746034084988471603868999706990048150305440277903164542478230684929369186215805784631115966687130130156185689872372352885092648612494977154218334204285686060146824720771435854874155657069677653720226485447015858801620758474922657226002085584466521458398893944370926591800311388246468157082630100594858704003186480342194897278290641045072636881313739855256117322040245091227700226941127573627280495738108967504018369868368450725799364729060762996941380475654823728997180326802474420629269124859052181004459842150591120249441341728531478105803603371077309182869314710171111683916581726889419758716582152128229518488472089694633862891562882765952635140542267653239694617511291602408715510135150455381287560052631468017127402653969470240300517495318862925631385188163478001569369176881852378684052287837629389214300655869568685964595155501644724509836896036887323114389415576651040883914292338113206052433629485317049915771756228549741438999188021762430965206564211827316726257539594717255934637238632261482742622208671155839599926521176252698917540988159348640083457085181472231814204070426509056532333398436457865796796519267292399875366617215982578860263363617827495994219403777753681426217738799194551397231274066898329989895386728822856378697749662519966583525776198939322845344735694794962952168891485492538904755828834526096524096542889394538646625744927556381964410316979833061852019379384940057156333720548068540575867999670121372239");
        assertPrecisionCalculation(
                expected,
                mathContext -> BigDecimalMath.sqrt(BD("2"), mathContext),
                10);
    }

    @Test
    public void testSqrtHuge() {
        // Result from wolframalpha.com: sqrt(1e399)
        BigDecimal expected = BD("3.1622776601683793319988935444327185337195551393252168E199");
        assertEquals(expected.round(MC), BigDecimalMath.sqrt(BD("1E399"), MC));
    }

    @Test
    public void testSqrtRandom() {
        assertRandomCalculation(
                adaptCount(1000),
                "sqrt",
                random -> random.nextDouble() * 100 + 0.000001,
                Math::sqrt,
                (x, mathContext) -> BigDecimalMath.sqrt(x, mathContext));
    }

	/*
    @Test
    public void testSqrtJava9Random() {
        assertRandomCalculation(
                adaptCount(1000),
                "sqrt(x)",
                "java9 sqrt(x)",
                (random, mathContext) -> randomBigDecimal(random, mathContext),
                (x, mathContext) -> BigDecimalMath.sqrt(x, mathContext),
                (x, mathContext) -> x.sqrt(mathContext));
    }
    */

    @Test(expected = ArithmeticException.class)
    public void testSqrtNegative() {
        BigDecimalMath.sqrt(BD(-1), MC);
    }

    @Test
    public void testRootSqrtCbrt() {
        for(double x : new double[] { 0, 0.1, 1, 2, 10, 33.3333 }) {
            assertBigDecimal(
                    "root(2," + x + ")",
                    toCheck(Math.sqrt(x)),
                    BigDecimalMath.root(BigDecimal.valueOf(x), BigDecimal.valueOf(2), MC),
                    MC_CHECK_DOUBLE);
            assertBigDecimal(
                    "root(3," + x + ")",
                    toCheck(Math.cbrt(x)),
                    BigDecimalMath.root(BigDecimal.valueOf(x), BigDecimal.valueOf(3), MC),
                    MC_CHECK_DOUBLE);
        }
    }

    @Test
    public void testSqrtPow2Random() {
        assertRandomCalculation(
                adaptCount(1000),
                "x",
                "pow(sqrt(x),2)",
                (random, mathContext) -> randomBigDecimal(random, mathContext),
                (x, mathContext) -> x,
                (x, mathContext) -> BigDecimalMath.pow(BigDecimalMath.sqrt(x, mathContext), 2, mathContext));
    }

    @Test
    public void testSqrtRootRandom() {
        BigDecimal value2 = BD("2");
        assertRandomCalculation(
                adaptCount(1000),
                "sqrt(x)",
                "root(x, 2)",
                (random, mathContext) -> randomBigDecimal(random, mathContext),
                (x, mathContext) -> BigDecimalMath.sqrt(x, mathContext),
                (x, mathContext) -> BigDecimalMath.root(x, value2, mathContext));
    }
}
