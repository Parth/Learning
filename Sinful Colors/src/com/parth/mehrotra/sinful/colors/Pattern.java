package com.parth.mehrotra.sinful.colors;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.view.View;

@SuppressLint({ "ParserError", "ParserError", "ParserError" })
public class Pattern extends View {

	Paint shade;

	int padding = 5;

	public int[] SHADE_ID = new int[180];

	Bitmap[] SHADE_PIC = new Bitmap[5];

	Rect rect[] = new Rect[180];

	Context c;
	int w;

	public Pattern(Context context, int w) {
		super(context);
		c = context;
		this.w = w;

		initShades();
		initRects();
		createBitmaps(0);
		invalidate();
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		for (int i = 0; i < 5; i++) {
			if (SHADE_PIC[i] != null) {
				canvas.drawBitmap(SHADE_PIC[i], null, rect[i], null);
				
			}
		}
	}

	public void changeBitmaps(int x) {
		createBitmaps(x);
	}

	public void freeUpMemory() {
		new Thread(new Runnable() {
			public void run() {
				// for (int i = 0; i < SHADE_PIC.length; i++) {
				// if (SHADE_PIC[i] != null)
				// SHADE_PIC[i].recycle();
				//
				// }
			}
		}).start();

		System.gc();
	}

	public void createBitmaps(int startingNumber) {
		for (int i = 0; i < 5; i++) {
			SHADE_PIC[i] = BitmapFactory.decodeResource(c.getResources(),
					SHADE_ID[(startingNumber*5) + i]);
			invalidate();
		}
	}

	public void initRects() {

		int y = 0;

		rect[0] = new Rect(0 + padding, (0 + padding) + y, ((w / 3) * 2)
				- padding, ((w / 3) - padding) + y);

		rect[1] = new Rect(((w / 3) * 2) + padding, (0 + padding) + y, w
				- padding, (((w / 3) * 2) - padding) + y);
		rect[2] = new Rect(((w / 3) + 5), (((w / 3) * 2) + padding) + y, w
				- padding, (w - padding) + y);

		rect[3] = new Rect(0 + padding, ((w / 3) + padding) + y, w / 3 - 5,
				(w - 5) + y);

		rect[4] = new Rect(w / 3 + padding, (w / 3 + padding) + y,
				((w / 3) * 2) - padding, (((w / 3) * 2) - padding) + y);
	}

	public void initShades() {
		SHADE_ID[0] = R.drawable.shades_hottie;

		SHADE_ID[1] = R.drawable.shades_pinkglitter;

		SHADE_ID[2] = R.drawable.shades_queenofbeauty;

		SHADE_ID[3] = R.drawable.shades_allaboutyou;

		SHADE_ID[4] = R.drawable.shades_opalglitter;
		SHADE_ID[5] = R.drawable.shades_callmelater;

		SHADE_ID[6] = R.drawable.shades_staringatstars;

		SHADE_ID[7] = R.drawable.shades_nailjunkie;

		SHADE_ID[8] = R.drawable.shades_imissyou;

		SHADE_ID[9] = R.drawable.shades_frenzy;

		SHADE_ID[10] = R.drawable.shades_kissy;

		SHADE_ID[11] = R.drawable.stripesrgb;// XXX

		SHADE_ID[12] = R.drawable.shades_secretadmirer;

		SHADE_ID[13] = R.drawable.shades_sugarsugar;

		SHADE_ID[14] = R.drawable.shades_ciaobella;

		SHADE_ID[15] = R.drawable.shades_purplediamond;

		SHADE_ID[16] = R.drawable.shades_forgetnow;

		SHADE_ID[17] = R.drawable.shades_cinderella;

		SHADE_ID[18] = R.drawable.shades_bluebyyou;

		SHADE_ID[19] = R.drawable.shades_glasspink;

		SHADE_ID[20] = R.drawable.shades_outofthisworld;

		SHADE_ID[21] = R.drawable.shades_socialladder;

		SHADE_ID[22] = R.drawable.shades_cloud9;

		SHADE_ID[23] = R.drawable.shades_darkbronze;

		SHADE_ID[24] = R.drawable.shades_fiji;

		SHADE_ID[25] = R.drawable.shades_happyending;

		SHADE_ID[26] = R.drawable.shades_letsmeet;

		SHADE_ID[27] = R.drawable.shades_letstalk;

		SHADE_ID[28] = R.drawable.shades_lovenails;

		SHADE_ID[29] = R.drawable.shades_twilight;

		SHADE_ID[30] = R.drawable.shades_islandcoral;

		SHADE_ID[31] = R.drawable.shades_youjustwait;

		SHADE_ID[32] = R.drawable.shades_thisisit;

		SHADE_ID[33] = R.drawable.shades_balimist;

		SHADE_ID[34] = R.drawable.shades_tokyopearl;

		SHADE_ID[35] = R.drawable.shades_sanfrancisco;

		SHADE_ID[36] = R.drawable.shades_winterberry;

		SHADE_ID[37] = R.drawable.shades_hdnails;

		SHADE_ID[38] = R.drawable.stripesrgb;// XXX

		SHADE_ID[39] = R.drawable.shades_oasis;

		SHADE_ID[40] = R.drawable.shades_shiningheart;

		SHADE_ID[41] = R.drawable.shades_bikini;

		SHADE_ID[42] = R.drawable.shades_flirtingnails;

		SHADE_ID[43] = R.drawable.shades_under18;

		SHADE_ID[44] = R.drawable.shades_richinheart;

		SHADE_ID[45] = R.drawable.stripesrgb;

		SHADE_ID[46] = R.drawable.stripesrgb;// XXX

		SHADE_ID[47] = R.drawable.stripesrgb;// XXX

		SHADE_ID[48] = R.drawable.stripesrgb;// XXX

		SHADE_ID[49] = R.drawable.stripesrgb;// XXX

		SHADE_ID[50] = R.drawable.stripesrgb; // XXX

		SHADE_ID[51] = R.drawable.stripesrgb; // XXX

		SHADE_ID[52] = R.drawable.stripesrgb; // XXX

		SHADE_ID[53] = R.drawable.stripesrgb; // XXX

		SHADE_ID[54] = R.drawable.stripesrgb;// XXX

		SHADE_ID[55] = R.drawable.stripesrgb;// XXX

		SHADE_ID[56] = R.drawable.stripesrgb;// XXX

		SHADE_ID[57] = R.drawable.stripesrgb;// XXX

		SHADE_ID[58] = R.drawable.stripesrgb;// XXX

		SHADE_ID[59] = R.drawable.stripesrgb;// XXX

		SHADE_ID[60] = R.drawable.stripesrgb;// XXX

		SHADE_ID[61] = R.drawable.stripesrgb;// XXX

		SHADE_ID[62] = R.drawable.stripesrgb;// XXX

		SHADE_ID[63] = R.drawable.stripesrgb;// XXX

		SHADE_ID[64] = R.drawable.stripesrgb;// XXX

		SHADE_ID[65] = R.drawable.stripesrgb;// XXX

		SHADE_ID[66] = R.drawable.stripesrgb;// XXX

		SHADE_ID[67] = R.drawable.stripesrgb;// XXX

		SHADE_ID[68] = R.drawable.stripesrgb;// XXX

		SHADE_ID[69] = R.drawable.stripesrgb;// XXX

		SHADE_ID[70] = R.drawable.stripesrgb;// XXX

		SHADE_ID[71] = R.drawable.stripesrgb;// XXX

		SHADE_ID[72] = R.drawable.stripesrgb;// XXX

		SHADE_ID[73] = R.drawable.stripesrgb;// XXX

		SHADE_ID[74] = R.drawable.stripesrgb;// XXX

		SHADE_ID[75] = R.drawable.stripesrgb;// XXX

		SHADE_ID[76] = R.drawable.stripesrgb;// XXX

		SHADE_ID[77] = R.drawable.stripesrgb;// XXX

		SHADE_ID[78] = R.drawable.stripesrgb;// XXX

		SHADE_ID[79] = R.drawable.stripesrgb;// XXX

		SHADE_ID[80] = R.drawable.stripesrgb;// XXX

		SHADE_ID[81] = R.drawable.stripesrgb;// XXX

		SHADE_ID[82] = R.drawable.stripesrgb;// XXX

		SHADE_ID[83] = R.drawable.shades_gorgeous;

		SHADE_ID[84] = R.drawable.shades_letmego;

		SHADE_ID[85] = R.drawable.shades_nirvana;

		SHADE_ID[86] = R.drawable.shades_grainedepoivre;

		SHADE_ID[87] = R.drawable.shades_247;

		SHADE_ID[88] = R.drawable.shades_dreamon;

		SHADE_ID[89] = R.drawable.shades_savage;

		SHADE_ID[90] = R.drawable.shades_casablanca;

		SHADE_ID[91] = R.drawable.shades_dancingnails;

		SHADE_ID[92] = R.drawable.shades_riseandshine;

		SHADE_ID[93] = R.drawable.shades_slate;

		SHADE_ID[94] = R.drawable.shades_sharonsheart;

		SHADE_ID[95] = R.drawable.shades_beaukhaki;

		SHADE_ID[96] = R.drawable.shades_beverlyhills;

		SHADE_ID[97] = R.drawable.shades_behappy;

		SHADE_ID[98] = R.drawable.stripesrgb;// XXX

		SHADE_ID[99] = R.drawable.stripesrgb;// XXX

		SHADE_ID[100] = R.drawable.shades_aubergine;// XXX CHECK

		SHADE_ID[101] = R.drawable.shades_amethyst;

		SHADE_ID[102] = R.drawable.stripesrgb;// XXX

		SHADE_ID[103] = R.drawable.shades_bluedepluirainstorm;

		SHADE_ID[104] = R.drawable.shades_bleudenuageblueskies;

		SHADE_ID[105] = R.drawable.stripesrgb;// XXX

		SHADE_ID[106] = R.drawable.shades_nudedusoirpalepeach;

		SHADE_ID[107] = R.drawable.shades_ciel;

		SHADE_ID[108] = R.drawable.shades_boomboom;

		SHADE_ID[109] = R.drawable.stripesrgb;

		SHADE_ID[110] = R.drawable.shades_folly;

		SHADE_ID[111] = R.drawable.shades_coolgrey;

		SHADE_ID[112] = R.drawable.shades_unicorn;

		SHADE_ID[113] = R.drawable.shades_feelinggreat;

		SHADE_ID[114] = R.drawable.shades_daredevil;

		SHADE_ID[115] = R.drawable.shades_camel;

		SHADE_ID[116] = R.drawable.shades_lastchance;

		SHADE_ID[117] = R.drawable.shades_courtneyorange;

		SHADE_ID[118] = R.drawable.shades_sunburnt;

		SHADE_ID[119] = R.drawable.shades_flyaway;

		SHADE_ID[120] = R.drawable.shades_cofee; // SPELLING

		SHADE_ID[121] = R.drawable.stripesrgb;// XXX

		SHADE_ID[122] = R.drawable.shades_crossmyheart;

		SHADE_ID[123] = R.drawable.shades_easygoing;

		SHADE_ID[124] = R.drawable.shades_exoticgreen;

		SHADE_ID[125] = R.drawable.shades_hazard;

		SHADE_ID[126] = R.drawable.shades_mauve;

		SHADE_ID[127] = R.drawable.shades_bleuelectriqueendlessblue;

		SHADE_ID[128] = R.drawable.shades_genteel;

		SHADE_ID[129] = R.drawable.shades_lavanderlavande;

		SHADE_ID[130] = R.drawable.shades_verbena;

		SHADE_ID[131] = R.drawable.shades_mintapple;

		SHADE_ID[132] = R.drawable.shades_poudre;

		SHADE_ID[133] = R.drawable.shades_creampink;

		SHADE_ID[134] = SHADE_ID[88]; // check

		SHADE_ID[135] = R.drawable.shades_irishgreenvertflou;

		SHADE_ID[136] = R.drawable.stripesrgb;

		SHADE_ID[137] = R.drawable.shades_pink;

		SHADE_ID[138] = R.drawable.shades_summerpeach;

		SHADE_ID[139] = R.drawable.shades_bigdaddy;

		SHADE_ID[140] = R.drawable.shades_soulmate;

		SHADE_ID[141] = R.drawable.shades_pistachkeylime;

		SHADE_ID[142] = R.drawable.shades_georgio;

		SHADE_ID[143] = R.drawable.shades_midnightblue;

		SHADE_ID[144] = R.drawable.shades_rubyruby;

		SHADE_ID[145] = R.drawable.shades_innocent;

		SHADE_ID[146] = R.drawable.stripesrgb;// XXX

		SHADE_ID[147] = R.drawable.stripesrgb;// XXX

		SHADE_ID[148] = R.drawable.shades_envy;

		SHADE_ID[149] = R.drawable.stripesrgb;// XXX

		SHADE_ID[150] = R.drawable.stripesrgb;// XXX

		SHADE_ID[151] = R.drawable.shades_pinkforever;

		SHADE_ID[152] = R.drawable.shades_vacationtime;

		SHADE_ID[153] = R.drawable.stripesrgb;// XXX

		SHADE_ID[154] = R.drawable.shades_bordeaux;

		SHADE_ID[155] = R.drawable.stripesrgb;// XXX

		SHADE_ID[156] = R.drawable.shades_gogogirl;

		SHADE_ID[157] = R.drawable.shades_nudedusoirpalepeach;

		SHADE_ID[158] = R.drawable.stripesrgb; // XXX

		SHADE_ID[159] = SHADE_ID[133]; // check

		SHADE_ID[160] = R.drawable.shades_starfish;

		SHADE_ID[161] = R.drawable.shades_openseas;

		SHADE_ID[162] = R.drawable.shades_whynot;

		SHADE_ID[163] = R.drawable.stripesrgb; // XXX

		SHADE_ID[164] = R.drawable.shades_violettedafriquesugarplum;

		SHADE_ID[165] = R.drawable.stripesrgb; // XXX

		SHADE_ID[166] = R.drawable.shades_pullover;

		SHADE_ID[167] = R.drawable.stripesrgb; // XXX

		SHADE_ID[168] = R.drawable.shades_rougecerisecalypso;

		SHADE_ID[169] = R.drawable.stripesrgb; // XXX

		SHADE_ID[170] = R.drawable.shades_blackonblack;

		SHADE_ID[171] = R.drawable.shades_fig;

		SHADE_ID[172] = R.drawable.shades_boogienights;

		SHADE_ID[173] = R.drawable.shades_upforanything;

		SHADE_ID[174] = R.drawable.shades_roseecaltglamour; // SPELLING

		SHADE_ID[175] = R.drawable.shades_timbleberry;

		SHADE_ID[165] = R.drawable.stripesrgb; // XXX

		SHADE_ID[177] = R.drawable.shades_rougeirisecarmen;

		SHADE_ID[178] = R.drawable.shades_purrouge;

		SHADE_ID[179] = R.drawable.shades_snowmewhite;
	}

}