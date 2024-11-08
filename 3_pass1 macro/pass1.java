
// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pass1 {
    public pass1() {
    }

    public static void main(String[] var0) {
        String var3 = null;
        String var4 = null;
        String var5 = null;
        String var6 = null;
        String[] var7 = new String[10];
        String[] var8 = new String[20];
        String[] var9 = new String[20];
        int[] var10 = new int[10];
        int var11 = 0;
        int var12 = 0;
        boolean var13 = false;
        int var14 = 1;
        boolean var15 = false;

        try {
            // Change the file path to just "Input.txt" (same folder as pass1.java)
            BufferedReader var1 = new BufferedReader(new FileReader("Input.txt"));
            File var16 = new File("mnt.txt");
            File var17 = new File("mdt.txt");
            File var18 = new File("adt.txt");
            PrintWriter var19 = new PrintWriter(var16);
            PrintWriter var20 = new PrintWriter(var17);

            PrintWriter var21;
            for (var21 = new PrintWriter(var18); (var3 = var1.readLine()) != null; ++var14) {
                StringTokenizer var22 = new StringTokenizer(var3, " ");
                var4 = var22.nextToken();
                StringTokenizer var23;
                if (var4.equals("MACRO")) {
                    var15 = true;
                    var4 = var22.nextToken();
                    var7[var11] = var4;
                    var10[var11] = var14;
                    var19.println(var7[var11] + "\t" + var10[var11]);
                    var20.println(var7[var11]);
                    var21.println(var7[var11]);
                    ++var11;
                    var4 = var22.nextToken();
                    var23 = new StringTokenizer(var4, ",");

                    while (var23.hasMoreTokens()) {
                        var5 = var23.nextToken();
                        if (var5.charAt(0) == '&') {
                            var9[var12] = var5;
                            var21.println(var9[var12]);
                            ++var12;
                        }
                    }
                } else if (var15) {
                    if (var3.equals("MEND")) {
                        var15 = false;
                        var20.println("MEND");
                    } else {
                        var23 = new StringTokenizer(var3, " ");

                        while (var23.hasMoreTokens()) {
                            var6 = var23.nextToken();

                            for (int var24 = 0; var24 < var12; ++var24) {
                                if (var6.charAt(0) == '&' && var6.equals(var9[var24])) {
                                    var20.print("AR" + var24);
                                }
                            }

                            if (var6.charAt(0) != '&') {
                                var20.print(var6 + " ");
                            }

                            if (!var23.hasMoreTokens()) {
                                var20.println();
                            }
                        }
                    }
                }
            }

            var19.close();
            var20.close();
            var21.close();
        } catch (Exception var25) {
            var25.printStackTrace();
        }

    }
}
