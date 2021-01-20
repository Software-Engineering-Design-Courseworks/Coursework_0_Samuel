package picture;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PictureProcessorTest {

  @Rule
  public TemporaryFolder tmpFolder = new TemporaryFolder();

  @Test
  public void help() {
    String expectedOutput = "To get help, write PictureProcessor help";
    ByteArrayOutputStream outstream = TestSuiteHelper.replaceSystemOutStreamForTesting();
    PictureProcessor.main(new String[0]);
    String actualOutput = outstream.toString();
    assertThat(actualOutput, containsString(expectedOutput));
  }

  @Test
  public void grayscaleBlack() throws IOException {
    assertEquals(
        new Picture("images/black64x64.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "grayscale", "images/black64x64.png"));
  }

  @Test
  public void grayscaleRainbow() throws IOException {
    assertEquals(
        new Picture("images/rainbowGS64x64doc.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "grayscale", "images/rainbow64x64doc.png"));
  }


  @Test
  public void rotate90Green() throws IOException {
    assertEquals(
            new Picture("images/green64x64R90doc.png"),
            TestSuiteHelper.getMainOutput(tmpFolder, "rotate", "90", "images/green64x64doc.png"));
  }



  @Test
  public void rotate90BlueRect() throws IOException {
    assertEquals(
            new Picture("images/blueR9064x32doc.png"),
            TestSuiteHelper.getMainOutput(tmpFolder, "rotate", "90", "images/blue64x32doc.png"));
  }

  @Test
  public void rotate180BlueRect() throws IOException {
    assertEquals(
            new Picture("images/blueR18064x32doc.png"),
            TestSuiteHelper.getMainOutput(tmpFolder, "rotate", "180", "images/blue64x32doc.png"));
  }

  @Test
  public void rotate270BlueRect() throws IOException {
    assertEquals(
            new Picture("images/blueR27064x32doc.png"),
            TestSuiteHelper.getMainOutput(tmpFolder, "rotate", "270", "images/blue64x32doc.png"));
  }


  /** More tests for optional picture transformations

  @Test
  public void invertBlack() throws IOException {
    assertEquals(
        new Picture("images/white64x64.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "invert", "images/black64x64.png"));
  }

  @Test
  public void invertRainbow() throws IOException {
    assertEquals(
        new Picture("images/rainbowI64x64doc.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "invert", "images/rainbow64x64doc.png"));
  }

  @Test
  public void flipVGreen() throws IOException {
    assertEquals(
        new Picture("images/green64x64FVdoc.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "flip", "V", "images/green64x64doc.png"));
  }

  @Test
  public void flipVBlue() throws IOException {
    assertEquals(
        new Picture("images/blueFV64x32doc.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "flip", "V", "images/blue64x32doc.png"));
  }

  @Test
  public void flipHBlue() throws IOException {
    assertEquals(
        new Picture("images/blueFH64x32doc.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "flip", "H", "images/blue64x32doc.png"));
  }

  @Test
  public void blurBWPatterns() throws IOException {
    assertEquals(
        new Picture("images/bwpatternsblur64x64.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "blur", "images/bwpatterns64x64.png"));
  }

  @Test
  public void blurSunset() throws IOException {
    assertEquals(
        new Picture("images/sunsetBlur64x32.png"),
        TestSuiteHelper.getMainOutput(tmpFolder, "blur", "images/sunset64x32.png"));
  }

  @Test
  public void blendBWAndRainbow() throws IOException {
    assertEquals(
        new Picture("images/rainbowpatternsblend64x64.png"),
        TestSuiteHelper.getMainOutput(
            tmpFolder, "blend", "images/bwpatterns64x64.png", "images/rainbow64x64doc.png"));
  }

  @Test
  public void blendRainbowSunset() throws IOException {
    assertEquals(
        new Picture("images/rainbowsunsetBlend.png"),
        TestSuiteHelper.getMainOutput(
            tmpFolder, "blend", "images/rainbow64x64doc.png", "images/sunset64x32.png"));
  }
  **/
}