/* -------------------------------------------------------------------------+
| Institution:    Imperial College London                                   |
| Programme:      MSc in Computing Science                                  |
| Course:         70055 - Software Engineering Design                       |
|                                                                           |
| Assignment:     Coursework 0 - Picture Processor                          |
| File Name:      PictureProcessor.java                                     |
| Authors:        Samuel Valdes Gutierrez  (sv1220)                         |
|                 Pongsakorn Siripornpitak  (ps2520)                        |
| Last Version:   16th November 2020                                        |
| Description:    Main Class for implementing picture processing tasks      |
----------------------------------------------------------------------------+*/


package picture;

import java.io.IOException;

public class PictureProcessor {

    public static void main(String[] args) {

        /* I) Auxiliar Variables */

        String outputConsoleMessage;        // output message in console
        String inputFile;                   // input file for image processing
        String outputFile;                  // output file for image processing

        int inputHeight;                    // height for input picture
        int inputWidth;                     // width for input picture
        int outputHeight;                   // height for output picture
        int outputWidth;                    // width for output picture
        int rotation;                       /* degree of rotation for output picture (90º, 180º
                                            or 270º) */
        int gray;                           // RGB gray color scale

        Color inputColor;                   // Color object for managing color of input pixel
        Color outputColor;                  // Color object for managing color of output pixel
        Picture inputPicture;               // Picture object that represent input picture
        Picture outputPicture;              // Picture object that represents output picture

        /* II) Check output depending command-line arguments */

        if (args.length == 0) {                     // Case 1 -> No command line arguments
            System.out.println("To get help, write PictureProcessor help");

        } else if (args[0].equals("help")) {        // Case 2 -> "help" as command line argument
            outputConsoleMessage = "\n"
                    + "****** Command-Line Options ******\n"
                    + "help                                       - displays this help menu\n"
                    + "grayscale <in> <out>                       - write to <out> a monochrome version"
                    + " <in>\n"
                    + "rotate 90|180|270 <in> <out>               - writes to <out> a rotated version"
                    + " of <in>\n"
                    + "invert <in> <out>                          - writes to <out> a inverted version"
                    + " of <in>\n"
                    + "flipped [H|V] <in> <out>                   - writes to <out> a flipped version"
                    + " of <in>\n"
                    + "blend <in_1> <in_2> ... <out>              - writes to <out> a blended version"
                    + " of <in>\n"
                    + "blur <in> <out>                            - writes to <out> a blurred version "
                    + " of <in>\n"
                    + "mosaic tile-size <in_1> <in_2> ... <out>   - writes to <out> a mosaic version "
                    + " of <in>\n";

            System.out.println(outputConsoleMessage);

        } else if (args[0].equals("grayscale"))  {  /* Case 3 -> "grayscale <in> <out>" as command
                                                    line argument*/
            try {
                inputFile = args[1];
                outputFile = args[2];

                inputPicture = new Picture(inputFile);
                inputWidth = inputPicture.getWidth();
                inputHeight = inputPicture.getHeight();

                outputWidth = inputWidth;
                outputHeight = inputHeight;
                outputPicture = new Picture(outputWidth, outputHeight);

                for (int j = 0; j < outputHeight; j++) {
                    for (int i = 0; i < outputWidth; i++) {
                        inputColor = inputPicture.getPixel(i,j);
                        gray = (inputColor.red() + inputColor.green() + inputColor.blue()) / 3;
                        outputColor = new Color(gray,gray,gray);
                        outputPicture.setPixel(i,j,outputColor);
                    }
                }

                outputPicture.saveAs(outputFile);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Not enough command line arguments - Caught IOException: "
                        +  e.getMessage());
            }

        } else if (args[0].equals("rotate")) {      /* Case 4 -> "rotate 90|180|270 <in> <out>" as
                                                    command line argument */

            try {
                rotation = Integer.parseInt(args[1]);  // convert string to integer
                inputFile = args[2];
                outputFile = args[3];

                inputPicture = new Picture(inputFile);
                inputWidth = inputPicture.getWidth();
                inputHeight = inputPicture.getHeight();


                if (rotation == 90 || rotation == 270) {    /* Output height and width for rotations
                                                             in 90º and 270º */
                    outputHeight = inputWidth;
                    outputWidth = inputHeight;

                } else {                                    /* Output height and width for rotations
                                                            in 180º */
                    outputWidth = inputWidth;
                    outputHeight = inputHeight;

                }

                outputPicture = new Picture(outputWidth, outputHeight);

                for (int j = 0; j < inputHeight; j++) {
                    for (int i = 0; i < inputWidth; i++) {

                        inputColor = inputPicture.getPixel(i,j);
                        outputColor = new Color(inputColor.red(),inputColor.green(),
                                inputColor.blue());

                        if (rotation == 90) {
                            outputPicture.setPixel((inputHeight - 1) - j,i,outputColor);
                        } else if (rotation == 180) {
                            outputPicture.setPixel((inputWidth - 1) - i,(inputHeight - 1) - j,
                                    outputColor);
                        } else if (rotation == 270) {
                            outputPicture.setPixel(j,(inputWidth - 1) - i,outputColor);
                        }
                    }
                }

                outputPicture.saveAs(outputFile);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Not enough command line arguments - Caught IOException: "
                        +  e.getMessage());
            }

        }

    }
}