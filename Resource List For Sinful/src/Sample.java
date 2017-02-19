import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Sample {

	public static int[][] COLORS = new int[180][3];
	public static String[] NAMES = new String[180];

	public static void main(String args[]) throws BiffException, IOException,
			RowsExceededException, WriteException {

		init();

		WritableWorkbook workbook = Workbook.createWorkbook(new File(
				"output2.xls"));

		WritableSheet sheet = workbook.createSheet("SHEET 1", 0);

		Number number = null;

		for (int i = 0; i < 540; i++) {
			number = new Number(0, i, (int) i / 3);
			sheet.addCell(number);
		}

		for (int i = 0; i < 540; i++) {
			number = new Number(1, i, (int) i % 3);
			sheet.addCell(number);
		}

		for (int i = 0; i < 540; i++) {
			number = new Number(2, i, COLORS[(int) i / 3][(int) i % 3]);
			sheet.addCell(number);

		}

		// for (int x = 0; x < 180; x++) {
		// for (int i = 0; i < 3; i++) {
		// number = new Number(0, x*(i+1), x);
		// sheet.addCell(number);
		// System.out.println(x*(3));
		// }
		// }
		//
		// for (int x = 0; x < 180; x++) {
		// for (int i = 0; i < 3; i++) {
		// number = new Number(1, x, i);
		// sheet.addCell(number);
		// }
		// }
		//
		// for (int x = 0; x < 180; x++) {
		// for (int i = 0; i < 3; i++) {
		// number = new Number(2, x, COLORS[x][i]);
		// sheet.addCell(number);
		// }
		// }

		workbook.write();
		workbook.close();
	}

	private static void init() {

		// Hottie;
		NAMES[0] = "Hottie";
		COLORS[0][0] = 127;
		COLORS[0][1] = 138;
		COLORS[0][2] = 182;

		// Pink Glitter;
		NAMES[1] = "Pink Glitter";
		COLORS[1][0] = 224;
		COLORS[1][1] = 134;
		COLORS[1][2] = 169;

		// Queen of beauty;
		NAMES[2] = "Queen Of Beauty";
		COLORS[2][0] = 153;
		COLORS[2][1] = 146;
		COLORS[2][2] = 146;

		// All About You
		NAMES[3] = "All About You";
		COLORS[3][0] = 207;
		COLORS[3][1] = 128;
		COLORS[3][2] = 70;

		// Opal Glitter;
		NAMES[4] = "Opal Glitter";
		COLORS[4][0] = 241;
		COLORS[4][1] = 240;
		COLORS[4][2] = 245;

		// Call you later;
		NAMES[5] = "Call You Later";
		COLORS[5][0] = 115;
		COLORS[5][1] = 131;
		COLORS[5][2] = 70;

		// Staring at stars
		NAMES[6] = "Staring at Stars";
		COLORS[6][0] = 196;
		COLORS[6][1] = 190;
		COLORS[6][1] = 192;

		// Nail Junkie
		NAMES[7] = "Nail Junkie";
		COLORS[7][0] = 118;
		COLORS[7][1] = 190;
		COLORS[7][2] = 213;

		// I Miss You
		NAMES[8] = "I Miss You";
		COLORS[8][0] = 155;
		COLORS[8][1] = 76;
		COLORS[8][2] = 105;

		// Frenzy
		NAMES[9] = "Frenzy";
		COLORS[9][0] = 131;
		COLORS[9][1] = 91;
		COLORS[9][2] = 133;

		// Kissy
		NAMES[10] = "Kissy";
		COLORS[10][0] = 74;
		COLORS[10][1] = 97;
		COLORS[10][2] = 92;

		// Reflecting Pool
		NAMES[11] = "Reflecting Pool";
		COLORS[11][0] = 172;
		COLORS[11][1] = 174;
		COLORS[11][2] = 180;

		// Secret Admirer
		NAMES[12] = "Secret Admirer";
		COLORS[12][0] = 62;
		COLORS[12][1] = 56;
		COLORS[12][2] = 56;

		// Sugar Sugar
		NAMES[13] = "Sugar Sugar";
		COLORS[13][0] = 156;
		COLORS[13][1] = 58;
		COLORS[13][2] = 66;

		// Ciao Bella
		NAMES[14] = "Ciao Bella";
		COLORS[14][0] = 60;
		COLORS[14][1] = 72;
		COLORS[14][2] = 130;

		// Purple Diamond
		NAMES[15] = "Purple Diamond";
		COLORS[15][0] = 178;
		COLORS[15][1] = 133;
		COLORS[15][2] = 178;

		// Forget Now
		NAMES[16] = "Forget Now";
		COLORS[16][0] = 184;
		COLORS[16][1] = 57;
		COLORS[16][2] = 92;

		// Cinderella
		NAMES[17] = "Cinderella";
		COLORS[17][0] = 183;
		COLORS[17][1] = 225;
		COLORS[17][2] = 243;

		// Blue By You
		NAMES[18] = "Blue By You";
		COLORS[18][0] = 60;
		COLORS[18][1] = 55;
		COLORS[18][2] = 98;

		// Glass Pink
		NAMES[19] = "Glass Pink";
		COLORS[19][0] = 253;
		COLORS[19][1] = 200;
		COLORS[19][2] = 217;

		// Out of this world
		NAMES[20] = "Out of this World";
		COLORS[20][0] = 202;
		COLORS[20][1] = 198;
		COLORS[20][2] = 200;

		// Social Ladder
		NAMES[21] = "Social Ladder";
		COLORS[21][0] = 238;
		COLORS[21][1] = 211;
		COLORS[21][2] = 210;

		// Cloud 9
		NAMES[22] = "Cloud 9";
		COLORS[22][0] = 243;
		COLORS[22][1] = 109;
		COLORS[22][2] = 74;

		// Dark Bronze
		NAMES[23] = "Dark Bronze";
		COLORS[23][0] = 104;
		COLORS[23][1] = 84;
		COLORS[23][2] = 77;

		// Fiji
		NAMES[24] = "Fiji";
		COLORS[24][0] = 62;
		COLORS[24][1] = 58;
		COLORS[24][2] = 109;

		// Happy Ending
		NAMES[25] = "Happy Ending";
		COLORS[25][0] = 97;
		COLORS[25][1] = 162;
		COLORS[25][2] = 80;

		// Let's Meet
		NAMES[26] = "Let's Meet";
		COLORS[26][0] = 250;
		COLORS[26][1] = 186;
		COLORS[26][2] = 80;

		// Let's Talk
		NAMES[27] = "Let's Talk";
		COLORS[27][0] = 61;
		COLORS[27][1] = 55;
		COLORS[27][2] = 109;

		// Love Nails
		NAMES[28] = "Love Nails";
		COLORS[28][0] = 80;
		COLORS[28][1] = 121;
		COLORS[28][2] = 158;

		// Twilight
		NAMES[29] = "Twilight";
		COLORS[29][0] = 124;
		COLORS[29][1] = 117;
		COLORS[29][2] = 108;

		// Island Coral
		NAMES[30] = "Island Coral";
		COLORS[30][0] = 238;
		COLORS[30][1] = 90;
		COLORS[30][2] = 65;

		// You Just Wait
		NAMES[31] = "You Just Wait";
		COLORS[31][0] = 251;
		COLORS[31][1] = 167;
		COLORS[31][2] = 188;

		// This is it
		NAMES[32] = "This is it";
		COLORS[32][0] = 230;
		COLORS[32][1] = 150;
		COLORS[32][2] = 80;

		// Bali Mist
		NAMES[33] = "Bali Mist";
		COLORS[33][0] = 169;
		COLORS[34][1] = 99;
		COLORS[34][2] = 141;

		// Tokyo Pearl
		NAMES[34] = "Tokyo Pearl";
		COLORS[34][0] = 238;
		COLORS[34][1] = 231;
		COLORS[34][2] = 226;

		// San Francisco
		NAMES[35] = "San Francisco";
		COLORS[35][0] = 60;
		COLORS[35][1] = 77;
		COLORS[35][2] = 58;

		// Winterberry
		NAMES[36] = "Winterberry";
		COLORS[36][0] = 98;
		COLORS[36][1] = 69;
		COLORS[37][2] = 78;

		// HD Nails
		NAMES[37] = "HD Nail";
		COLORS[37][0] = 74;
		COLORS[37][1] = 129;
		COLORS[37][2] = 90;

		// Merlot
		NAMES[38] = "Merlot";
		COLORS[38][0] = 113;
		COLORS[38][1] = 57;
		COLORS[38][2] = 66;

		// Oasis
		NAMES[39] = "Oasis";
		COLORS[39][0] = 201;
		COLORS[39][1] = 57;
		COLORS[39][2] = 105;

		// Shining Heart
		NAMES[40] = "Shining Heart";
		COLORS[40][0] = 220;
		COLORS[40][1] = 61;
		COLORS[40][2] = 81;

		// Bikini
		NAMES[41] = "Bikini";
		COLORS[41][0] = 163;
		COLORS[41][1] = 58;
		COLORS[41][2] = 107;

		// Flirting Nails
		NAMES[42] = "Flirting Nails";
		COLORS[42][0] = 173;
		COLORS[42][1] = 131;
		COLORS[42][2] = 107;

		// Under 18
		NAMES[43] = "Under 18";
		COLORS[43][0] = 193;
		COLORS[43][1] = 60;
		COLORS[43][2] = 62;

		// Rich in Heart
		NAMES[44] = "Rich in Heart";
		COLORS[44][0] = 193;
		COLORS[44][1] = 60;
		COLORS[44][2] = 62;

		// Bonjour
		NAMES[45] = "Bonjour";
		COLORS[45][0] = 116;
		COLORS[45][1] = 170;
		COLORS[45][2] = 161;

		// Fun Times
		NAMES[46] = "Fun Times";
		COLORS[46][0] = 64;
		COLORS[46][1] = 52;
		COLORS[46][2] = 95;

		// Having Fun
		NAMES[47] = "Having Fun";
		COLORS[47][0] = 213;
		COLORS[47][1] = 133;
		COLORS[47][2] = 150;

		// Anxious Azure
		NAMES[48] = "Anxious Azure";
		COLORS[48][0] = 80;
		COLORS[48][1] = 116;
		COLORS[48][2] = 151;

		// Sunset
		NAMES[49] = "Sunset";
		COLORS[49][0] = 226;
		COLORS[49][1] = 75;
		COLORS[49][2] = 62;

		// Slice to go
		NAMES[50] = "A Slice To Go";
		COLORS[50][0] = 206;
		COLORS[50][1] = 58;
		COLORS[50][2] = 95;

		// Worn Before
		NAMES[51] = "Worn Before";
		COLORS[51][0] = 93;
		COLORS[51][1] = 150;
		COLORS[51][2] = 74;

		// Pot of Gold
		NAMES[52] = "Pot of Gold";
		COLORS[52][0] = 227;
		COLORS[52][1] = 178;
		COLORS[52][2] = 103;

		// Rose in Your Nose
		NAMES[53] = "Rose in your Nose";
		COLORS[53][0] = 231;
		COLORS[53][1] = 107;
		COLORS[53][2] = 150;

		// Hey Dear
		NAMES[54] = "Hey Dear!";
		COLORS[54][0] = 84;
		COLORS[54][1] = 124;
		COLORS[54][2] = 156;

		// Sour Grapes
		NAMES[55] = "Sour Grapes";
		COLORS[55][0] = 109;
		COLORS[55][1] = 55;
		COLORS[55][2] = 111;

		// No More
		NAMES[56] = "No More";
		COLORS[56][0] = 81;
		COLORS[56][1] = 115;
		COLORS[56][2] = 159;

		// Orange Alert
		NAMES[57] = "Orange Alert";
		COLORS[57][0] = 241;
		COLORS[57][1] = 103;
		COLORS[57][2] = 77;

		// Hot Mess
		NAMES[58] = "Hot Mess";
		COLORS[58][0] = 235;
		COLORS[58][1] = 84;
		COLORS[58][2] = 130;

		// Jungle
		NAMES[59] = "Jungle";
		COLORS[59][0] = 86;
		COLORS[59][1] = 143;
		COLORS[59][2] = 116;

		// Bad Chick
		NAMES[60] = "Bad Chick";
		COLORS[60][0] = 246;
		COLORS[60][1] = 244;
		COLORS[60][2] = 250;

		// Fashionista
		NAMES[61] = "Fashionista";
		COLORS[61][0] = 170;
		COLORS[61][1] = 169;
		COLORS[61][2] = 170;

		// Sunny Day
		NAMES[62] = "Sunny Day";
		COLORS[62][0] = 221;
		COLORS[62][1] = 62;
		COLORS[62][2] = 75;

		// Take Me To Heaven
		NAMES[63] = "Take Me To Heaven";
		COLORS[63][0] = 76;
		COLORS[63][1] = 118;
		COLORS[63][2] = 100;

		// Peace n Love
		NAMES[64] = "Peace n Love";
		COLORS[64][0] = 241;
		COLORS[64][1] = 110;
		COLORS[64][2] = 133;

		// Pretty Cool
		NAMES[65] = "Pretty Cool";
		COLORS[65][0] = 122;
		COLORS[65][1] = 62;
		COLORS[70][2] = 70;

		// Me First
		NAMES[66] = "Me First";
		COLORS[66][0] = 246;
		COLORS[66][1] = 246;
		COLORS[66][2] = 88;

		// Moon Light
		NAMES[67] = "Moon Light";
		COLORS[67][0] = 121;
		COLORS[67][1] = 79;
		COLORS[67][2] = 134;

		// Morning Breath
		NAMES[68] = "Morning Breath";
		COLORS[68][0] = 250;
		COLORS[68][1] = 170;
		COLORS[68][2] = 89;

		// Flower Girl
		NAMES[69] = "Flower Girl";
		COLORS[69][0] = 206;
		COLORS[69][1] = 134;
		COLORS[69][2] = 79;

		// Love Me
		NAMES[70] = "Love Me";
		COLORS[70][0] = 129;
		COLORS[70][1] = 105;
		COLORS[70][2] = 144;

		// Time Off
		NAMES[71] = "Time Off";
		COLORS[71][0] = 47;
		COLORS[71][1] = 47;
		COLORS[71][2] = 45;

		// Time 2 go
		NAMES[72] = "Time 2 Go";
		COLORS[72][0] = 208;
		COLORS[72][1] = 59;
		COLORS[72][2] = 62;

		// Simple but Fun
		NAMES[73] = "Simple but Fun";
		COLORS[73][0] = 84;
		COLORS[73][1] = 138;
		COLORS[73][2] = 85;

		// Sour Apple
		NAMES[74] = "Sour Apple";
		COLORS[74][0] = 131;
		COLORS[74][1] = 186;
		COLORS[74][2] = 80;

		// Wild Night
		NAMES[75] = "Wild Night";
		COLORS[75][0] = 245;
		COLORS[75][1] = 149;
		COLORS[75][2] = 180;

		// Be my Valentine
		NAMES[76] = "Be my Valentine";
		COLORS[76][0] = 184;
		COLORS[76][1] = 59;
		COLORS[76][2] = 62;

		// What a Night
		NAMES[77] = "What a Night";
		COLORS[77][0] = 200;
		COLORS[77][1] = 197;
		COLORS[77][2] = 202;

		// Top Secret
		NAMES[78] = "Top Secret";
		COLORS[78][0] = 239;
		COLORS[78][1] = 168;
		COLORS[78][2] = 72;

		// True Love
		NAMES[79] = "True Love";
		COLORS[79][0] = 194;
		COLORS[79][1] = 57;
		COLORS[79][2] = 104;

		// Lagoon
		NAMES[80] = "Lagoon";
		COLORS[80][0] = 66;
		COLORS[80][1] = 63;
		COLORS[80][2] = 116;

		// Lazy Girl
		NAMES[81] = "Lazy Girl";
		COLORS[81][0] = 75;
		COLORS[81][1] = 65;
		COLORS[81][2] = 65;

		// My Day
		NAMES[82] = "My Day";
		COLORS[82][0] = 196;
		COLORS[82][1] = 64;
		COLORS[82][2] = 65;

		// Gorgeous
		NAMES[83] = "Gorgeous";
		COLORS[83][0] = 93;
		COLORS[83][1] = 154;
		COLORS[83][2] = 131;

		// Let Me Go
		NAMES[84] = "Let Me Go";
		COLORS[84][0] = 235;
		COLORS[84][1] = 174;
		COLORS[84][2] = 177;

		// Nirvana Taupe
		NAMES[85] = "Nirvana Taupe";
		COLORS[85][0] = 139;
		COLORS[85][1] = 117;
		COLORS[85][2] = 105;

		// Graine de Poivre Willow
		NAMES[86] = "Graine de Poivre Willow";
		COLORS[86][0] = 91;
		COLORS[86][1] = 88;
		COLORS[86][2] = 73;

		// 27/7
		NAMES[87] = "27/7";
		COLORS[87][0] = 242;
		COLORS[87][1] = 79;
		COLORS[87][2] = 128;

		// Dream On
		NAMES[88] = "Dream On";
		COLORS[88][0] = 113;
		COLORS[88][1] = 59;
		COLORS[88][2] = 113;

		// Savage
		NAMES[89] = "Savage";
		COLORS[89][0] = 97;
		COLORS[89][1] = 172;
		COLORS[89][2] = 164;

		// Casablanca
		NAMES[90] = "Casablanca";
		COLORS[90][0] = 197;
		COLORS[90][1] = 193;
		COLORS[90][2] = 195;

		// Dancing Nail
		NAMES[91] = "Dancing Nails";
		COLORS[91][0] = 136;
		COLORS[91][1] = 57;
		COLORS[91][2] = 59;

		// Rise & Shine
		NAMES[92] = "Rise & Shine";
		COLORS[92][0] = 85;
		COLORS[92][1] = 135;
		COLORS[92][2] = 115;

		// Slate
		NAMES[93] = "Slate";
		COLORS[93][0] = 115;
		COLORS[93][1] = 110;
		COLORS[93][2] = 113;

		// Sharons Heart
		NAMES[94] = "Sharons";
		COLORS[94][0] = 178;
		COLORS[94][1] = 61;
		COLORS[94][2] = 60;

		// Beau Khaki
		NAMES[95] = "Beau Khaki";
		COLORS[95][0] = 107;
		COLORS[95][1] = 114;
		COLORS[95][2] = 96;

		// Beverly Hills
		NAMES[96] = "Beverly Hills";
		COLORS[96][0] = 207;
		COLORS[96][1] = 131;
		COLORS[96][2] = 171;

		// Be Happy;
		NAMES[97] = "Be Happy";
		COLORS[97][0] = 128;
		COLORS[97][1] = 196;
		COLORS[97][2] = 172;

		// Chlorophylle
		NAMES[98] = "Chlorophylle";
		COLORS[98][0] = 132;
		COLORS[98][1] = 200;
		COLORS[98][2] = 181;

		// Anemone
		NAMES[99] = "Anemone";
		COLORS[99][0] = 227;
		COLORS[99][1] = 96;
		COLORS[99][2] = 79;

		// Aubergine Luscious
		NAMES[100] = "Aubergine Luscious";
		COLORS[100][0] = 169;
		COLORS[100][1] = 57;
		COLORS[100][2] = 85;

		// Amethyst
		NAMES[101] = "Amethyst";
		COLORS[101][0] = 106;
		COLORS[101][1] = 85;
		COLORS[101][2] = 129;

		// Beau Brique Jungle Red
		NAMES[102] = "Beau Brique Jungle Red";
		COLORS[102][0] = 144;
		COLORS[102][1] = 57;
		COLORS[102][2] = 55;

		// Bleu de Pluie Rain Storm
		NAMES[103] = "Bleu de Pluie Rain Storm";
		COLORS[103][0] = 65;
		COLORS[103][1] = 76;
		COLORS[103][2] = 119;

		// Bleu de Nuage Blue Skies
		NAMES[104] = "Bleu de Nuage Blue Skies";
		COLORS[104][0] = 114;
		COLORS[104][1] = 134;
		COLORS[104][2] = 184;

		// Bubbly
		NAMES[105] = "Bubbly";
		COLORS[105][0] = 252;
		COLORS[105][1] = 233;
		COLORS[105][2] = 198;

		// Nude
		NAMES[106] = "Nude";
		COLORS[106][0] = 196;
		COLORS[106][1] = 134;
		COLORS[106][2] = 113;

		// Ciel
		NAMES[107] = "Ciel";
		COLORS[107][0] = 219;
		COLORS[107][1] = 226;
		COLORS[107][2] = 234;

		// Boom Boom
		NAMES[108] = "Boom Boom";
		COLORS[108][0] = 199;
		COLORS[108][1] = 58;
		COLORS[108][2] = 109;

		// Ardoise Night Magic
		NAMES[109] = "Ardoise Night Magic";
		COLORS[109][0] = 67;
		COLORS[109][1] = 71;
		COLORS[109][2] = 80;

		// Folly
		NAMES[110] = "Folly";
		COLORS[110][0] = 193;
		COLORS[110][1] = 60;
		COLORS[110][2] = 76;

		// Cool Gray
		NAMES[111] = "Cool Gray";
		COLORS[111][0] = 156;
		COLORS[111][1] = 149;
		COLORS[111][2] = 144;

		// Unicorn
		NAMES[112] = "Unicorn";
		COLORS[112][0] = 255;
		COLORS[112][1] = 235;
		COLORS[112][2] = 158;

		// Feeling Great
		NAMES[113] = "Feeling Great";
		COLORS[113][0] = 199;
		COLORS[113][1] = 60;
		COLORS[113][2] = 82;

		// Daredevil
		NAMES[114] = "Daredevil";
		COLORS[114][0] = 234;
		COLORS[114][1] = 84;
		COLORS[114][2] = 126;

		// Camel
		NAMES[115] = "Camel";
		COLORS[115][0] = 105;
		COLORS[115][1] = 82;
		COLORS[115][2] = 66;

		// Last Chance
		NAMES[116] = "Last Chance";
		COLORS[116][0] = 72;
		COLORS[116][1] = 98;
		COLORS[116][2] = 68;

		// Courtney Orange
		NAMES[117] = "Courney Orange";
		COLORS[117][0] = 236;
		COLORS[117][1] = 90;
		COLORS[117][2] = 66;

		// Sunburnt
		NAMES[118] = "Sunburnt";
		COLORS[118][0] = 233;
		COLORS[118][1] = 60;
		COLORS[118][2] = 78;

		// Fly Away
		NAMES[119] = "Fly Away";
		COLORS[119][0] = 91;
		COLORS[119][1] = 123;
		COLORS[119][2] = 183;

		// Coffee
		NAMES[120] = "Coffee";
		COLORS[120][0] = 81;
		COLORS[120][1] = 63;
		COLORS[120][2] = 74;

		// Navy I Do
		NAMES[121] = "Navy I Do";
		COLORS[121][0] = 59;
		COLORS[121][1] = 61;
		COLORS[121][2] = 86;

		// Cross My Heart
		NAMES[122] = "Cross My Heart";
		COLORS[122][0] = 227;
		COLORS[122][1] = 62;
		COLORS[122][2] = 65;

		// Easy Going
		NAMES[123] = "Easy Going";
		COLORS[123][0] = 253;
		COLORS[123][1] = 197;
		COLORS[123][2] = 212;

		// Exotic Green
		NAMES[124] = "Exotic Green";
		COLORS[124][0] = 92;
		COLORS[124][1] = 154;
		COLORS[124][2] = 75;

		// Hazard
		NAMES[125] = "Hazard";
		COLORS[125][0] = 240;
		COLORS[125][1] = 101;
		COLORS[125][2] = 69;

		// Mauve
		NAMES[126] = "Mauve";
		COLORS[126][0] = 180;
		COLORS[126][1] = 170;
		COLORS[126][2] = 193;

		// Bleu Electrique Endless Blue
		NAMES[127] = "Bleu Electrique Endless Blue";
		COLORS[127][0] = 69;
		COLORS[127][1] = 77;
		COLORS[127][2] = 131;

		// Genteel
		NAMES[128] = "Genteel";
		COLORS[128][0] = 247;
		COLORS[128][1] = 159;
		COLORS[129][2] = 129;

		// Lavander Lavande
		NAMES[129] = "Lavander Lavande";
		COLORS[129][0] = 119;
		COLORS[129][1] = 133;
		COLORS[129][2] = 162;

		// Verbena
		NAMES[130] = "Verbena";
		COLORS[130][0] = 122;
		COLORS[130][1] = 78;
		COLORS[130][2] = 133;

		// Mint Apple
		NAMES[131] = "Mint Apple";
		COLORS[131][0] = 100;
		COLORS[131][1] = 177;
		COLORS[131][2] = 140;

		// Poudre
		NAMES[132] = "Poudre";
		COLORS[132][0] = 252;
		COLORS[132][1] = 178;
		COLORS[132][2] = 170;

		// Cream Pink
		NAMES[133] = "Cream Pink";
		COLORS[133][0] = 198;
		COLORS[133][1] = 60;
		COLORS[133][2] = 96;

		// Dream On
		NAMES[134] = "Dream On";
		COLORS[134][0] = 98;
		COLORS[134][1] = 56;
		COLORS[134][2] = 102;

		// Irish Green Vert Flou
		NAMES[135] = "Irish Green Vert Flou";
		COLORS[135][0] = 89;
		COLORS[135][1] = 154;
		COLORS[135][2] = 84;

		// Neon Melon Jaune Flou
		NAMES[136] = "Neon Melon Jaune Flou";
		COLORS[136][0] = 217;
		COLORS[136][1] = 210;
		COLORS[136][2] = 98;

		// Pink
		NAMES[137] = "Pink";
		COLORS[137][0] = 227;
		COLORS[137][1] = 77;
		COLORS[137][2] = 128;

		// Summer Peach
		NAMES[138] = "Sunner Peach";
		COLORS[138][0] = 236;
		COLORS[138][1] = 111;
		COLORS[138][2] = 70;

		// Big Daddy
		NAMES[139] = "Big Daddy";
		COLORS[139][0] = 230;
		COLORS[139][1] = 87;
		COLORS[139][2] = 65;

		// Soul Mate
		NAMES[140] = "Soul Mate";
		COLORS[140][0] = 227;
		COLORS[140][1] = 112;
		COLORS[140][2] = 115;

		// Pistach Key Lime
		NAMES[141] = "Pistache Key Lime";
		COLORS[141][0] = 129;
		COLORS[141][1] = 194;
		COLORS[141][2] = 110;

		// Georgio
		NAMES[142] = "Georgio";
		COLORS[142][0] = 107;
		COLORS[142][1] = 57;
		COLORS[142][2] = 74;

		// Midnight Blue
		NAMES[143] = "Midnight Blue";
		COLORS[143][0] = 74;
		COLORS[143][1] = 82;
		COLORS[143][2] = 144;

		// Ruby Ruby
		NAMES[144] = "Ruby Ruby";
		COLORS[144][0] = 205;
		COLORS[144][1] = 63;
		COLORS[144][2] = 69;

		// Innocent
		NAMES[145] = "Innocent";
		COLORS[145][0] = 140;
		COLORS[145][1] = 198;
		COLORS[145][2] = 76;

		// Jaune D'ete Winstone
		NAMES[146] = "Jaune D'ete Winstone";
		COLORS[146][0] = 254;
		COLORS[146][1] = 196;
		COLORS[146][2] = 104;

		// Rose Capri Candy
		NAMES[147] = "Rose Capri Candy";
		COLORS[147][0] = 242;
		COLORS[147][1] = 90;
		COLORS[147][2] = 92;

		// Envy
		NAMES[148] = "Envy";
		COLORS[148][0] = 87;
		COLORS[148][1] = 148;
		COLORS[148][2] = 120;

		// Mandarine Bright Orange
		NAMES[149] = "Madarine Bright Orange";
		COLORS[149][0] = 246;
		COLORS[149][1] = 137;
		COLORS[149][2] = 77;

		// Jaune Pastel
		NAMES[150] = "Jaune Pastel";
		COLORS[150][0] = 250;
		COLORS[150][1] = 233;
		COLORS[150][2] = 151;

		// Pink Forever
		NAMES[151] = "Pink Forever";
		COLORS[151][0] = 252;
		COLORS[151][1] = 178;
		COLORS[151][2] = 172;

		// Vacation Time
		NAMES[152] = "Vacation Times";
		COLORS[152][0] = 198;
		COLORS[152][1] = 131;
		COLORS[152][2] = 114;

		// Lagon
		NAMES[153] = "Lagon";
		COLORS[153][0] = 181;
		COLORS[153][1] = 201;
		COLORS[153][2] = 184;

		// Bordeaux
		NAMES[154] = "Bordeaux";
		COLORS[154][0] = 149;
		COLORS[154][1] = 76;
		COLORS[154][2] = 75;

		// Kaki
		NAMES[155] = "Kaki";
		COLORS[155][0] = 122;
		COLORS[155][1] = 121;
		COLORS[155][2] = 97;

		// Gogo Girl
		NAMES[156] = "Gogo Girl";
		COLORS[156][0] = 204;
		COLORS[156][1] = 63;
		COLORS[156][2] = 73;

		// Nude du Soir Pale Peach
		NAMES[157] = "Nude du Soir Pale Peach";
		COLORS[157][0] = 205;
		COLORS[157][1] = 163;
		COLORS[157][2] = 116;

		// Ocean Side
		NAMES[158] = "Ocean Side";
		COLORS[158][0] = 70;
		COLORS[158][1] = 96;
		COLORS[158][2] = 130;

		// Cream Pink
		NAMES[159] = "Cream Pink";
		COLORS[159][0] = 226;
		COLORS[159][1] = 65;
		COLORS[159][2] = 118;

		// Starfish
		NAMES[160] = "Starfish";
		COLORS[160][0] = 207;
		COLORS[160][1] = 135;
		COLORS[160][2] = 159;

		// Open Seas
		NAMES[161] = "Open Seas";
		COLORS[161][0] = 132;
		COLORS[161][1] = 159;
		COLORS[161][2] = 139;

		// Why Not
		NAMES[162] = "Why Not";
		COLORS[162][0] = 78;
		COLORS[162][1] = 107;
		COLORS[162][2] = 162;

		// Paprika
		NAMES[163] = "Paprika";
		COLORS[163][0] = 241;
		COLORS[163][1] = 102;
		COLORS[163][2] = 73;

		// Violette d'Afrique Sugar Plum
		NAMES[164] = "Violette d'Afrique Sugar Plum";
		COLORS[164][0] = 143;
		COLORS[164][1] = 112;
		COLORS[164][2] = 166;

		// Virai Orange Orange Burst
		NAMES[165] = "Virai Orange Orange Burst";
		COLORS[165][0] = 235;
		COLORS[165][1] = 98;
		COLORS[165][2] = 64;

		// Pull Over
		NAMES[166] = "Pull Over";
		COLORS[166][0] = 252;
		COLORS[166][1] = 163;
		COLORS[166][2] = 71;

		// Orange Epicee Tango Tangerine;
		NAMES[167] = "Orange Epicee Tango Tangerine";
		COLORS[167][0] = 236;
		COLORS[167][1] = 92;
		COLORS[167][2] = 68;

		// Rouge Cerise Calypso
		NAMES[168] = "Rouge Cerise Calyso";
		COLORS[168][0] = 99;
		COLORS[168][1] = 69;
		COLORS[168][2] = 63;

		// Framboise Flambee Fuschia Kiss
		NAMES[169] = "Framboise Flambee Fuschia Kiss";
		COLORS[169][0] = 204;
		COLORS[169][1] = 60;
		COLORS[169][2] = 95;

		// Black on Black
		NAMES[170] = "Black on Black";
		COLORS[170][0] = 60;
		COLORS[170][1] = 60;
		COLORS[170][2] = 60;

		// Fig
		NAMES[171] = "Fig";
		COLORS[171][0] = 101;
		COLORS[171][1] = 66;
		COLORS[171][2] = 96;

		// Boogie Night
		NAMES[172] = "Boogie Nights";
		COLORS[172][0] = 237;
		COLORS[172][1] = 82;
		COLORS[172][2] = 70;

		// Up For Anything
		NAMES[173] = "Up For Anything";
		COLORS[173][0] = 193;
		COLORS[173][1] = 67;
		COLORS[173][2] = 61;

		// Rose Eclat Glamour
		NAMES[174] = "Rose Eclat Glamour";
		COLORS[174][0] = 211;
		COLORS[174][1] = 67;
		COLORS[174][2] = 88;

		// Timbleberry
		NAMES[175] = "TimbleBerry";
		COLORS[175][0] = 236;
		COLORS[175][1] = 67;
		COLORS[175][2] = 78;

		// Pale
		NAMES[176] = "Pale";
		COLORS[176][0] = 253;
		COLORS[176][1] = 220;
		COLORS[176][2] = 220;

		// Rouge Irise Carmen
		NAMES[177] = "Rouge Irise Carmen";
		COLORS[177][0] = 153;
		COLORS[177][1] = 66;
		COLORS[177][2] = 55;

		// Pur Rouge
		NAMES[178] = "Pur Rouge";
		COLORS[178][0] = 205;
		COLORS[178][1] = 65;
		COLORS[178][2] = 86;

		// Snow Me White
		NAMES[179] = "Snow Me White";
		COLORS[179][0] = 255;
		COLORS[179][1] = 255;
		COLORS[179][2] = 255;

	}

}