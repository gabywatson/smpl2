package smpl.lang;
import smpl.sys.*;
import java_cup.runtime.*;
import java.io.*;


public class SmplLexer implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

    int comNestLvl;
    public int getChar()
    {
	return yychar + 1;
    }
    public int getLine()
    {
	return yyline + 1;
    }
    public String getText()
    {
	return yytext();
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public SmplLexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public SmplLexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private SmplLexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int STRING = 2;
	private final int YYINITIAL = 0;
	private final int COMMENT = 1;
	private final int yy_state_dtrans[] = {
		0,
		161,
		107
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NOT_ACCEPT,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NOT_ACCEPT,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR,
		/* 218 */ YY_NO_ANCHOR,
		/* 219 */ YY_NO_ANCHOR,
		/* 220 */ YY_NO_ANCHOR,
		/* 221 */ YY_NO_ANCHOR,
		/* 222 */ YY_NO_ANCHOR,
		/* 223 */ YY_NO_ANCHOR,
		/* 224 */ YY_NO_ANCHOR,
		/* 225 */ YY_NO_ANCHOR,
		/* 226 */ YY_NO_ANCHOR,
		/* 227 */ YY_NO_ANCHOR,
		/* 228 */ YY_NO_ANCHOR,
		/* 229 */ YY_NO_ANCHOR,
		/* 230 */ YY_NO_ANCHOR,
		/* 231 */ YY_NO_ANCHOR,
		/* 232 */ YY_NO_ANCHOR,
		/* 233 */ YY_NO_ANCHOR,
		/* 234 */ YY_NO_ANCHOR,
		/* 235 */ YY_NO_ANCHOR,
		/* 236 */ YY_NO_ANCHOR,
		/* 237 */ YY_NO_ANCHOR,
		/* 238 */ YY_NO_ANCHOR,
		/* 239 */ YY_NO_ANCHOR,
		/* 240 */ YY_NO_ANCHOR,
		/* 241 */ YY_NO_ANCHOR,
		/* 242 */ YY_NO_ANCHOR,
		/* 243 */ YY_NO_ANCHOR,
		/* 244 */ YY_NO_ANCHOR,
		/* 245 */ YY_NO_ANCHOR,
		/* 246 */ YY_NO_ANCHOR,
		/* 247 */ YY_NO_ANCHOR,
		/* 248 */ YY_NO_ANCHOR,
		/* 249 */ YY_NO_ANCHOR,
		/* 250 */ YY_NO_ANCHOR,
		/* 251 */ YY_NO_ANCHOR,
		/* 252 */ YY_NO_ANCHOR,
		/* 253 */ YY_NO_ANCHOR,
		/* 254 */ YY_NO_ANCHOR,
		/* 255 */ YY_NO_ANCHOR,
		/* 256 */ YY_NO_ANCHOR,
		/* 257 */ YY_NO_ANCHOR,
		/* 258 */ YY_NO_ANCHOR,
		/* 259 */ YY_NO_ANCHOR,
		/* 260 */ YY_NO_ANCHOR,
		/* 261 */ YY_NO_ANCHOR,
		/* 262 */ YY_NO_ANCHOR,
		/* 263 */ YY_NO_ANCHOR,
		/* 264 */ YY_NO_ANCHOR,
		/* 265 */ YY_NO_ANCHOR,
		/* 266 */ YY_NO_ANCHOR,
		/* 267 */ YY_NO_ANCHOR,
		/* 268 */ YY_NO_ANCHOR,
		/* 269 */ YY_NO_ANCHOR,
		/* 270 */ YY_NO_ANCHOR,
		/* 271 */ YY_NO_ANCHOR,
		/* 272 */ YY_NO_ANCHOR,
		/* 273 */ YY_NO_ANCHOR,
		/* 274 */ YY_NO_ANCHOR,
		/* 275 */ YY_NO_ANCHOR,
		/* 276 */ YY_NO_ANCHOR,
		/* 277 */ YY_NO_ANCHOR,
		/* 278 */ YY_NO_ANCHOR,
		/* 279 */ YY_NO_ANCHOR,
		/* 280 */ YY_NO_ANCHOR,
		/* 281 */ YY_NO_ANCHOR,
		/* 282 */ YY_NO_ANCHOR,
		/* 283 */ YY_NO_ANCHOR,
		/* 284 */ YY_NO_ANCHOR,
		/* 285 */ YY_NO_ANCHOR,
		/* 286 */ YY_NO_ANCHOR,
		/* 287 */ YY_NO_ANCHOR,
		/* 288 */ YY_NO_ANCHOR,
		/* 289 */ YY_NO_ANCHOR,
		/* 290 */ YY_NO_ANCHOR,
		/* 291 */ YY_NO_ANCHOR,
		/* 292 */ YY_NO_ANCHOR,
		/* 293 */ YY_NO_ANCHOR,
		/* 294 */ YY_NO_ANCHOR,
		/* 295 */ YY_NO_ANCHOR,
		/* 296 */ YY_NO_ANCHOR,
		/* 297 */ YY_NO_ANCHOR,
		/* 298 */ YY_NO_ANCHOR,
		/* 299 */ YY_NO_ANCHOR,
		/* 300 */ YY_NO_ANCHOR,
		/* 301 */ YY_NO_ANCHOR,
		/* 302 */ YY_NO_ANCHOR,
		/* 303 */ YY_NO_ANCHOR,
		/* 304 */ YY_NO_ANCHOR,
		/* 305 */ YY_NO_ANCHOR,
		/* 306 */ YY_NO_ANCHOR,
		/* 307 */ YY_NO_ANCHOR,
		/* 308 */ YY_NO_ANCHOR,
		/* 309 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"4:8,68,70,1,4,69,67,4:18,2,33,15,8,4,26,28,4,42,43,5,25,72,6,13,3,12:2,7:8," +
"40,71,35,36,37,47,38,60,61,62,10,63,58,66:9,57,66,64,66,59,66:2,56,66:3,39," +
"14,41,27,66,4,29,11,21,30,24,23,66,53,46,66,55,20,65,16,32,19,49,22,18,17,5" +
"1,50,54,9,52,48,44,31,45,34,4,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,310,
"0,1:3,2,1,3,4,5,6,7,1:3,8,9,10,1,11,1,12,1,13,14,1:7,15,1:5,16,17,1,18,1:8," +
"16,19,20,1,16:7,21,1:4,22,16:3,23,16:4,24,16,1,16:3,25,1,16:8,1,16:13,1:3,2" +
"6,27,1:4,17,16,4,17,16,7,16:2,28,29,30,19,31,1,32,33,20,34,16,35,36,37,38,3" +
"9,7,40,41,42,43,44,45,21,46,47,22,48,49,50,47,51,52,53,54,55,56,57,58,59,60" +
",61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85" +
",86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107," +
"108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126" +
",127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,14" +
"5,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,1" +
"64,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182," +
"183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201" +
",202,203,204,205,206,207,208")[0];

	private int yy_nxt[][] = unpackFromString(209,73,
"1,2,3,4,5,6,7,8,9,114:2,125,8,116,5,10,197,200,238,130,202,204,240,206,242," +
"11,12,13,14,208,210,15,134,16,17,18,19,20,21,22,23,24,25,26,27,28,138,126,1" +
"14:6,263,114:10,279,114,2,3:3,29,30,-1:76,31,-1,32,-1:70,33,-1:76,115,-1:4," +
"115,-1:67,8,-1:4,8,113,-1:68,124,-1,129,-1:2,133,-1:2,34,-1:5,35,36,-1:50,1" +
"37:12,141,39,137:57,-1:28,41,-1:75,42,-1:77,43,-1:72,44,-1:72,45,-1:76,46,-" +
"1:41,144:3,-1:4,144:9,-1:4,144:2,-1,144,-1:3,47,-1:4,48,-1:4,144,-1,144:19," +
"-1:8,31:65,-1,31:5,-1:9,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131" +
":19,-1:13,38,-1:4,38,-1:69,131:3,-1:4,131:9,-1:4,131:2,-1,162,-1:13,216,-1," +
"131:19,-1:13,50,-1:2,50:3,-1:8,50,-1,50:2,-1:4,50:2,-1:27,50,-1,50:4,-1:21," +
"51,-1:69,60:3,-1:4,60:9,-1:4,60:2,-1,60,-1:13,60,-1,60:19,-1:13,151,-1:2,15" +
"1:3,-1:8,151,-1,151:2,-1:4,151:2,-1:27,151,-1,151:4,-1:18,131:3,-1:4,131:9," +
"-1:4,131:2,-1,131,-1:13,131,81,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1," +
"131,-1:13,228,-1,131:19,-1:15,131:3,-1:4,131:4,186,131:4,-1:4,131:2,-1,131," +
"-1:13,131,-1,131:19,-1:6,1,-1,123:12,108,109,123:51,-1,123:5,-1:15,110,111," +
"112,-1:62,147,-1:2,147:3,-1:8,147,-1,147:2,-1:4,147:2,-1:27,147,-1,147:4,-1" +
":14,105,-1:69,123:12,-1:2,123:51,-1,123:5,-1:9,131:3,-1:4,131:6,239,131,37," +
"-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:26,61,-1:55,106,-1:78,131:3,-1:4,1" +
"31:6,40,131:2,-1:4,203,131,-1,131,-1:13,131,-1,131:19,-1:17,62,-1:64,52:4,1" +
"21,52:2,121:3,52:3,127,132,136,52:2,140,52,143,121,52:4,121:2,52:27,121,52," +
"121:4,52:3,-1:4,52:2,-1:9,131:3,-1:4,131:6,120,131:2,-1:4,131:2,-1,131,-1:1" +
"3,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:6" +
",53,131:12,-1:25,63,-1:62,131:3,-1:4,131:7,49,131,-1:4,131:2,-1,131,-1:13,1" +
"31,-1,131:19,-1:15,131:3,-1:4,131,117,131:7,-1:4,131:2,-1,131,-1:13,131,-1," +
"131:19,-1:13,147,-1:2,147:3,-1:8,147,64,147:2,-1:4,147:2,-1:27,147,-1,147:4" +
",-1:11,137:12,141,118,137:52,199,137:4,-1:9,131:3,-1:4,131:9,-1:4,131:2,-1," +
"131,-1:13,131,-1,131:4,54,131:14,-1:13,147,-1:2,147:3,-1:8,147,-1,65,147,-1" +
":4,147:2,-1:27,147,-1,147:4,-1:18,131:3,-1:4,131,55,131:7,-1:4,131:2,-1,131" +
",-1:13,131,-1,131:19,-1:13,146,-1:2,146:3,-1:8,146,-1,146:2,-1:4,146:2,-1:2" +
"7,146,-1,146:4,-1:18,131:3,-1:4,131,218,166,131,167,131,56,131:2,-1:4,131:2" +
",-1,131,-1:13,131,-1,131:19,-1:7,153,137:12,141,39,137:57,-1:9,131:3,-1:4,1" +
"31:6,57,131:2,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:6," +
"58,131:2,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:73,155,-1:14,131:3,-1:4,1" +
"31:9,-1:4,131,119,-1,131,-1:13,131,-1,131:19,-1:76,157,-1:11,131:3,-1:4,131" +
":7,59,131,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:8,159,-1:79,131:3,-1:4,6" +
"6,131:8,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:8,159,-1:11,137,-1:67,131:" +
"3,-1:4,131:8,67,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:6,1,104:2,122,104," +
"128,104:67,-1:9,131:3,-1:4,131:5,68,131:3,-1:4,131:2,-1,131,-1:13,131,-1,13" +
"1:19,-1:15,131:3,-1:4,131:6,69,131:2,-1:4,131:2,-1,131,-1:13,131,-1,131:19," +
"-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:4,70,131:14,-1:15" +
",131:3,-1:4,131,71,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3," +
"-1:4,131:8,72,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:4," +
"73,131:4,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4," +
"131,74,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:8,75,-1:4,131:2,-1,1" +
"31,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,7" +
"6,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:7,77,131" +
":11,-1:15,131:3,-1:4,131,78,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:" +
"15,131:3,-1:4,131:3,79,131:5,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,13" +
"1:3,-1:4,131,80,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:" +
"4,131:2,82,131:6,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131" +
":9,-1:4,131:2,-1,131,-1:13,131,-1,131:5,83,131:13,-1:15,131:3,-1:4,131:8,84" +
",-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131,85,-" +
"1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:6,86,131:2,-1:4,131:2,-1,131" +
",-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131,87,-1,131,-1:13,131,-1" +
",131:19,-1:15,131:3,-1:4,131:6,88,131:2,-1:4,131:2,-1,131,-1:13,131,-1,131:" +
"19,-1:15,131:3,-1:4,131,89,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:1" +
"5,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,90,131:19,-1:15,131:3,-1:4,1" +
"31:9,-1:4,131,91,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131" +
",92,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,93,131:8,-1:4,131:2,-1,131," +
"-1:13,131,-1,131:19,-1:15,131:3,-1:4,131,94,131:7,-1:4,131:2,-1,131,-1:13,1" +
"31,-1,131:19,-1:15,131:3,-1:4,95,131:8,-1:4,131:2,-1,131,-1:13,131,-1,131:1" +
"9,-1:15,131:3,-1:4,131:6,96,131:2,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:" +
"15,131:3,-1:4,131:9,-1:4,97,131,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4" +
",131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:6,98,131:12,-1:15,131:3,-1:4,131:" +
"5,99,131:3,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:3,100" +
",131:5,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,13" +
"1:2,-1,131,-1:13,131,-1,131:6,101,131:12,-1:15,131:3,-1:4,131,102,131:7,-1:" +
"4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131,103,131:7,-1:4,131:" +
"2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:8,135,-1:4,131:2,-1,139,-" +
"1:13,131,-1,131:19,-1:15,131:3,-1:4,131:6,246,131,158,-1:4,131:2,-1,131,-1:" +
"13,131,-1,131:19,-1:8,137:12,141,39,137:53,149,137:3,-1:9,131:3,-1:4,131:6," +
"142,131:2,-1:4,131:2,-1,131,-1:13,131,-1,131:5,198,131:13,-1:15,131:3,-1:4," +
"131:9,-1:4,131:2,-1,131,-1:13,131,-1,160,131:18,-1:15,131:3,-1:4,131:8,145," +
"-1:4,205,131,-1,131,-1:13,207,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-" +
"1,131,-1:13,163,-1,131:19,-1:15,131:3,-1:4,131:4,244,131,307,131:2,-1:4,148" +
",150,-1,266,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1" +
":13,131,-1,164,131:18,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,152,-1:13,131,-1" +
",131:19,-1:15,131:3,-1:4,131:2,165,131:6,-1:4,131:2,-1,131,-1:13,131,-1,131" +
":19,-1:15,131:3,-1:4,154,131:2,268,131:5,-1:4,131,296,-1,131,-1:13,131,-1,1" +
"31:19,-1:15,131:3,-1:4,131:9,-1:4,168,131,-1,131,-1:13,131,-1,131:19,-1:15," +
"131:3,-1:4,131:8,156,-1:4,308,131,-1,131,-1:13,309,-1,131:19,-1:15,131:3,-1" +
":4,131:2,169,131:6,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,1" +
"31:9,-1:4,131:2,-1,131,-1:13,131,-1,131:2,170,251,131:15,-1:15,131:3,-1:4,1" +
"31:9,-1:4,171,131,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:6,172,131" +
":2,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:8,173,-1:4,13" +
"1:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,174,131:8,-1:4,131:2,-1,131" +
",-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:2,175,131:6,-1:4,131:2,-1,131,-1:" +
"13,131,-1,131:19,-1:15,131:3,-1:4,131:5,176,131:3,-1:4,131:2,-1,131,-1:13,1" +
"31,-1,131:19,-1:15,131:3,-1:4,131:4,177,131:4,-1:4,131:2,-1,131,-1:13,131,-" +
"1,131:19,-1:15,131:3,-1:4,131:9,-1:4,178,131,-1,131,-1:13,131,-1,131:19,-1:" +
"15,131:3,-1:4,131,179,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131" +
":3,-1:4,131:8,180,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,13" +
"1,181,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:6,18" +
"2,131:2,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:4,183,13" +
"1:4,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,184,131:8,-1:4,1" +
"31:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,185,-1" +
":13,131,-1,131:19,-1:15,131:3,-1:4,187,131:8,-1:4,131:2,-1,131,-1:13,131,-1" +
",131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,188,-1:13,131,-1,131:19,-1:15," +
"131:3,-1:4,131:9,-1:4,189,131,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,1" +
"31:8,190,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4," +
"131:2,-1,191,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:8,192,-1:4,131:2,-1,1" +
"31,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-" +
"1,131:3,193,131:15,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,194,-1:13,131,-1,13" +
"1:19,-1:15,131:3,-1:4,131:4,195,131:4,-1:4,131:2,-1,131,-1:13,131,-1,131:19" +
",-1:15,131:3,-1:4,131:5,196,131:3,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:" +
"15,131:3,-1:4,131,241,131:2,243,131:4,-1:4,131:2,-1,131,-1:13,201,-1,131:3," +
"264,131,265,131:13,-1:15,131:3,-1:4,131:8,213,-1:4,131:2,-1,131,-1:13,131,-" +
"1,131:19,-1:15,131:3,-1:4,131:8,209,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-" +
"1:15,131:3,-1:4,131:9,-1:4,214,131,-1,131,-1:13,131,-1,131:19,-1:15,267,131" +
":2,-1:4,131:4,211,131:4,-1:4,131:2,-1,131,-1:13,131,-1,131,212,131:17,-1:15" +
",131:3,-1:4,131:8,215,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:" +
"4,131:9,-1:4,217,131,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4" +
",131:2,-1,131,-1:13,219,-1,131:19,-1:15,131:3,-1:4,131:8,220,-1:4,131:2,-1," +
"131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:2,221,131:6,-1:4,131:2,-1,131," +
"-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:6,222,131:2,-1:4,131:2,-1,131,-1:1" +
"3,131,-1,131:19,-1:15,131:3,-1:4,131:2,223,131:6,-1:4,131:2,-1,131,-1:13,13" +
"1,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,224,-1:13,131,-1,131:19,-1" +
":15,131:3,-1:4,131:9,-1:4,225,131,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1" +
":4,131:8,226,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-" +
"1:4,131:2,-1,131,-1:13,131,-1,131:5,227,131:13,-1:15,131:3,-1:4,131,229,131" +
":7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:2,230,-1:4,131:9,-1:4,13" +
"1:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:6,231,131:2,-1:4,131:2," +
"-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131,232,-1,131,-1:13" +
",131,-1,131:19,-1:15,233,131:2,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,13" +
"1:19,-1:15,131:3,-1:4,131:3,234,131:5,-1:4,131:2,-1,131,-1:13,131,-1,131:19" +
",-1:15,131:3,-1:4,131:9,-1:4,131,235,-1,131,-1:13,131,-1,131:19,-1:15,131:3" +
",-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:3,236,131:15,-1:15,131:3,-1:" +
"4,131:8,237,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1" +
":4,131:2,-1,131,-1:13,131,-1,131:5,245,131:13,-1:15,131:2,247,-1:4,131:9,-1" +
":4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,248,131,-1," +
"131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,249,131:8,-1:4,131:2,-1,131,-1:13," +
"131,-1,131:19,-1:15,131:3,-1:4,131:3,250,131:5,-1:4,131:2,-1,131,-1:13,131," +
"-1,131:19,-1:15,131:3,-1:4,131:3,252,131:5,-1:4,131:2,-1,131,-1:13,131,-1,1" +
"31:19,-1:15,131:3,-1:4,131,253,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19," +
"-1:15,131:3,-1:4,131,254,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15," +
"131:3,-1:4,131:8,255,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4" +
",131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:12,256,131:6,-1:15,131:3,-1:4,257" +
",131:8,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,13" +
"1:2,-1,131,-1:13,131,-1,131:14,295,258,287,131:2,-1:15,131:3,-1:4,131:9,-1:" +
"4,131:2,-1,259,-1:13,131,-1,131:19,-1:15,131:3,-1:4,260,131:8,-1:4,131:2,-1" +
",131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:2,261,131:6,-1:4,131:2,-1,131" +
",-1:13,131,-1,131:19,-1:15,131:3,-1:4,262,131:8,-1:4,131:2,-1,131,-1:13,131" +
",-1,131:19,-1:15,131:3,-1:4,131:8,269,-1:4,131:2,-1,131,-1:13,131,-1,131:19" +
",-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:3,270,131:15,-1:" +
"15,131:3,-1:4,131:4,271,131:4,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,1" +
"31:3,-1:4,131,272,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-" +
"1:4,131:9,-1:4,131:2,-1,131,-1:13,273,-1,131:19,-1:15,131:3,-1:4,131:8,274," +
"-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1," +
"131,-1:13,131,-1,131:8,286,275,131:9,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,1" +
"31,-1:13,276,-1,131:19,-1:15,131:3,-1:4,131:8,277,-1:4,131:2,-1,131,-1:13,1" +
"31,-1,131:19,-1:15,131:3,-1:4,278,131:8,-1:4,131:2,-1,131,-1:13,131,-1,131:" +
"19,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:10,290,297,131" +
",280,131:5,-1:15,131:3,-1:4,131:9,-1:4,131:2,-1,131,-1:13,281,-1,131:19,-1:" +
"15,282,131:2,-1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-" +
"1:4,131:9,-1:4,131:2,-1,131,-1:13,131,-1,131:8,283,131:10,-1:15,131:3,-1:4," +
"131:2,284,131:6,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:" +
"9,-1:4,131:2,-1,131,-1:13,131,-1,131:4,285,131:14,-1:15,131:3,-1:4,131:9,-1" +
":4,131:2,-1,288,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,131,289,-1," +
"131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:8,291,-1:4,131:2,-1,131,-1:13," +
"131,-1,131:19,-1:15,131:3,-1:4,131:8,292,-1:4,131:2,-1,131,-1:13,131,-1,131" +
":19,-1:15,131:3,-1:4,131:9,-1:4,293,131,-1,131,-1:13,131,-1,131:19,-1:15,13" +
"1:3,-1:4,131:9,-1:4,294,131,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131" +
",298,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:2,299,-1:4,131:9" +
",-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:4,300,131:4,-1:" +
"4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,301,131,-1,1" +
"31,-1:13,131,-1,131:19,-1:15,131:3,-1:4,131:9,-1:4,302,131,-1,131,-1:13,131" +
",-1,131:19,-1:15,131:3,-1:4,131:3,303,131:5,-1:4,131:2,-1,131,-1:13,131,-1," +
"131:19,-1:15,131:3,-1:4,131:8,304,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:" +
"15,131:3,-1:4,131,305,131:7,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:15,131" +
":3,-1:4,131:2,306,131:6,-1:4,131:2,-1,131,-1:13,131,-1,131:19,-1:6");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

    	return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
					 yychar = 0;
					}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return new Symbol(sym.DIVIDE);}
					case -5:
						break;
					case 5:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -6:
						break;
					case 6:
						{return new Symbol(sym.MULTIPLY);}
					case -7:
						break;
					case 7:
						{return new Symbol(sym.MINUS);}
					case -8:
						break;
					case 8:
						{return new Symbol(sym.INTEGER,
						new Integer (yytext()));}
					case -9:
						break;
					case 9:
						{return new Symbol (sym.ID, yytext());}
					case -10:
						break;
					case 10:
						{yybegin(STRING); return new Symbol(sym.OPENQUOTE);}
					case -11:
						break;
					case 11:
						{return new Symbol(sym.PLUS);}
					case -12:
						break;
					case 12:
						{return new Symbol(sym.MODULO);}
					case -13:
						break;
					case 13:
						{return new Symbol(sym.POWER);}
					case -14:
						break;
					case 14:
						{return new Symbol(sym.BAND);}
					case -15:
						break;
					case 15:
						{return new Symbol(sym.BOR);}
					case -16:
						break;
					case 16:
						{return new Symbol(sym.NOT);}
					case -17:
						break;
					case 17:
						{return new Symbol(sym.BNOT);}
					case -18:
						break;
					case 18:
						{return new Symbol(sym.LT);}
					case -19:
						break;
					case 19:
						{return new Symbol(sym.EQ);}
					case -20:
						break;
					case 20:
						{return new Symbol(sym.GT);}
					case -21:
						break;
					case 21:
						{return new Symbol(sym.CONCAT);}
					case -22:
						break;
					case 22:
						{return new Symbol(sym.LBRACKET);}
					case -23:
						break;
					case 23:
						{return new Symbol(sym.COLON);}
					case -24:
						break;
					case 24:
						{return new Symbol(sym.RBRACKET);}
					case -25:
						break;
					case 25:
						{return new Symbol(sym.LPAREN);}
					case -26:
						break;
					case 26:
						{return new Symbol(sym.RPAREN);}
					case -27:
						break;
					case 27:
						{return new Symbol(sym.LBRACE);}
					case -28:
						break;
					case 28:
						{return new Symbol(sym.RBRACE);}
					case -29:
						break;
					case 29:
						{return new Symbol(sym.SEMI);}
					case -30:
						break;
					case 30:
						{return new Symbol(sym.COMMA);}
					case -31:
						break;
					case 31:
						{/* ignore inline comments */}
					case -32:
						break;
					case 32:
						{
					  yybegin(COMMENT);
					  cmntNestLvl = 0;
					 }
					case -33:
						break;
					case 33:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": '*/' Trying to terminate block comment " +
					    "that doesn't exist. ");
					}
					case -34:
						break;
					case 34:
						{return new Symbol(sym.TRUE);}
					case -35:
						break;
					case 35:
						{return new Symbol(sym.FALSE);}
					case -36:
						break;
					case 36:
						{return new Symbol(sym.NIL);}
					case -37:
						break;
					case 37:
						{return new Symbol(sym.BE);}
					case -38:
						break;
					case 38:
						{return new Symbol(sym.DOUBLE,
						new Double (yytext()));}
					case -39:
						break;
					case 39:
						{String str = yytext().substring(1,yytext().length() - 1);
                                        return new Symbol  (sym.STRING, new String(str)); }
					case -40:
						break;
					case 40:
						{return tok(sym.PAIR);}
					case -41:
						break;
					case 41:
						{return new Symbol(sym.AND);}
					case -42:
						break;
					case 42:
						{return new Symbol(sym.OR);}
					case -43:
						break;
					case 43:
						{return new Symbol(sym.NE);}
					case -44:
						break;
					case 44:
						{return new Symbol(sym.LE);}
					case -45:
						break;
					case 45:
						{return new Symbol(sym.GE);}
					case -46:
						break;
					case 46:
						{return new Symbol(sym.VLBRACKET);}
					case -47:
						break;
					case 47:
						{return new Symbol(sym.ASSIGN);}
					case -48:
						break;
					case 48:
						{return new Symbol(sym.VRBRACKET);}
					case -49:
						break;
					case 49:
						{return new Symbol(sym.IF);}
					case -50:
						break;
					case 50:
						{return new Symbol(sym.HEXINT,
						new Integer (Integer.parseInt(yytext().substring(2), 16)));}
					case -51:
						break;
					case 51:
						{return new Symbol(sym.BININT,
						new Integer (Integer.parseInt(yytext().substring(2), 2)));}
					case -52:
						break;
					case 52:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -53:
						break;
					case 53:
						{return new Symbol(sym.NEW);}
					case -54:
						break;
					case 54:
						{return new Symbol(sym.TRY);}
					case -55:
						break;
					case 55:
						{return new Symbol(sym.LET);}
					case -56:
						break;
					case 56:
						{return new Symbol(sym.CAR);}
					case -57:
						break;
					case 57:
						{return new Symbol(sym.CDR);}
					case -58:
						break;
					case 58:
						{return new Symbol(sym.FOR);}
					case -59:
						break;
					case 59:
						{return new Symbol(sym.DEF);}
					case -60:
						break;
					case 60:
						{return new Symbol (sym.VAR, yytext());}
					case -61:
						break;
					case 61:
						{return new Symbol(sym.NEWLINE);}
					case -62:
						break;
					case 62:
						{return new Symbol(sym.TAB);}
					case -63:
						break;
					case 63:
						{return new Symbol(sym.SPACE);}
					case -64:
						break;
					case 64:
						{return new Symbol(sym.CRETURN);}
					case -65:
						break;
					case 65:
						{return new Symbol(sym.FORMFEED);}
					case -66:
						break;
					case 66:
						{return new Symbol(sym.THEN);}
					case -67:
						break;
					case 67:
						{return new Symbol(sym.SIZE);}
					case -68:
						break;
					case 68:
						{return new Symbol(sym.PROC);}
					case -69:
						break;
					case 69:
						{return new Symbol(sym.PAIR);}
					case -70:
						break;
					case 70:
						{return new Symbol (sym.LAZY);}
					case -71:
						break;
					case 71:
						{return new Symbol(sym.LIST);}
					case -72:
						break;
					case 72:
						{return new Symbol(sym.CASE);}
					case -73:
						break;
					case 73:
						{return new Symbol(sym.CALL);}
					case -74:
						break;
					case 74:
						{return new Symbol(sym.READ);}
					case -75:
						break;
					case 75:
						{return new Symbol(sym.ELSE);}
					case -76:
						break;
					case 76:
						{return new Symbol(sym.ISEQV);}
					case -77:
						break;
					case 77:
						{return new Symbol(sym.BREAK);}
					case -78:
						break;
					case 78:
						{return new Symbol(sym.START);}
					case -79:
						break;
					case 79:
						{return new Symbol(sym.SLEEP);}
					case -80:
						break;
					case 80:
						{return new Symbol(sym.PRINT);}
					case -81:
						break;
					case 81:
						{return new Symbol(sym.ISPAIR);}
					case -82:
						break;
					case 82:
						{return new Symbol(sym.CLASS);}
					case -83:
						break;
					case 83:
						{return new Symbol(sym.CATCH);}
					case -84:
						break;
					case 84:
						{return new Symbol(sym.WHILE);}
					case -85:
						break;
					case 85:
						{return new Symbol(sym.THREAD);}
					case -86:
						break;
					case 86:
						{return new Symbol(sym.SUBSTR);}
					case -87:
						break;
					case 87:
						{return new Symbol(sym.SHARED);}
					case -88:
						break;
					case 88:
						{return new Symbol(sym.CONSTRUCTOR);}
					case -89:
						break;
					case 89:
						{return new Symbol(sym.EXPORT);}
					case -90:
						break;
					case 90:
						{return new Symbol(sym.ISEQ);}
					case -91:
						break;
					case 91:
						{return new Symbol(sym.APPEND);}
					case -92:
						break;
					case 92:
						{return new Symbol(sym.METHOD);}
					case -93:
						break;
					case 93:
						{return new Symbol(sym.PRINTLN);}
					case -94:
						break;
					case 94:
						{return new Symbol(sym.READINT);}
					case -95:
						break;
					case 95:
						{return new Symbol(sym.ADDBUTTON);}
					case -96:
						break;
					case 96:
						{return new Symbol(sym.ADDFILEBAR);}
					case -97:
						break;
					case 97:
						{return new Symbol(sym.ADDTEXTAREA);}
					case -98:
						break;
					case 98:
						{return new Symbol(sym.CREATEWINDOW);}
					case -99:
						break;
					case 99:
						{return new Symbol(sym.DATABASEEXEC);}
					case -100:
						break;
					case 100:
						{return new Symbol(sym.DISPLAYPOPUP);}
					case -101:
						break;
					case 101:
						{return new Symbol(sym.DISPLAYWINDOW);}
					case -102:
						break;
					case 102:
						{return new Symbol(sym.DATABASERESULT);}
					case -103:
						break;
					case 103:
						{return new Symbol(sym.DATABASECONNECT);}
					case -104:
						break;
					case 104:
						{/* ignore everything else */}
					case -105:
						break;
					case 105:
						{cmntNestLvl++;}
					case -106:
						break;
					case 106:
						{
					  if (cmntNestLvl > 0)
					      cmntNestLvl--;
					  else {
					      yybegin(YYINITIAL);
					  }
					}
					case -107:
						break;
					case 107:
						{return new Symbol(sym.STRING, yytext());}
					case -108:
						break;
					case 108:
						{return new Symbol(sym.STRING, "\\");}
					case -109:
						break;
					case 109:
						{yybegin(YYINITIAL); return new Symbol(sym.CLOSEQUOTE);}
					case -110:
						break;
					case 110:
						{return new Symbol(sym.STRING, "\"");}
					case -111:
						break;
					case 111:
						{return new Symbol(sym.STRING, "\n");}
					case -112:
						break;
					case 112:
						{return new Symbol(sym.STRING, "\t");}
					case -113:
						break;
					case 114:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -114:
						break;
					case 115:
						{return new Symbol(sym.INTEGER,
						new Integer (yytext()));}
					case -115:
						break;
					case 116:
						{return new Symbol (sym.ID, yytext());}
					case -116:
						break;
					case 117:
						{return new Symbol(sym.NOT);}
					case -117:
						break;
					case 118:
						{String str = yytext().substring(1,yytext().length() - 1);
                                        return new Symbol  (sym.STRING, new String(str)); }
					case -118:
						break;
					case 119:
						{return new Symbol(sym.AND);}
					case -119:
						break;
					case 120:
						{return new Symbol(sym.OR);}
					case -120:
						break;
					case 121:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -121:
						break;
					case 122:
						{/* ignore everything else */}
					case -122:
						break;
					case 123:
						{return new Symbol(sym.STRING, yytext());}
					case -123:
						break;
					case 125:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -124:
						break;
					case 126:
						{return new Symbol (sym.ID, yytext());}
					case -125:
						break;
					case 127:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -126:
						break;
					case 128:
						{/* ignore everything else */}
					case -127:
						break;
					case 130:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -128:
						break;
					case 131:
						{return new Symbol (sym.ID, yytext());}
					case -129:
						break;
					case 132:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -130:
						break;
					case 134:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -131:
						break;
					case 135:
						{return new Symbol (sym.ID, yytext());}
					case -132:
						break;
					case 136:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -133:
						break;
					case 138:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -134:
						break;
					case 139:
						{return new Symbol (sym.ID, yytext());}
					case -135:
						break;
					case 140:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -136:
						break;
					case 142:
						{return new Symbol (sym.ID, yytext());}
					case -137:
						break;
					case 143:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -138:
						break;
					case 145:
						{return new Symbol (sym.ID, yytext());}
					case -139:
						break;
					case 146:
						{return new Symbol(sym.CHAR, yytext().substring(2));}
					case -140:
						break;
					case 148:
						{return new Symbol (sym.ID, yytext());}
					case -141:
						break;
					case 150:
						{return new Symbol (sym.ID, yytext());}
					case -142:
						break;
					case 152:
						{return new Symbol (sym.ID, yytext());}
					case -143:
						break;
					case 154:
						{return new Symbol (sym.ID, yytext());}
					case -144:
						break;
					case 156:
						{return new Symbol (sym.ID, yytext());}
					case -145:
						break;
					case 158:
						{return new Symbol (sym.ID, yytext());}
					case -146:
						break;
					case 160:
						{return new Symbol (sym.ID, yytext());}
					case -147:
						break;
					case 162:
						{return new Symbol (sym.ID, yytext());}
					case -148:
						break;
					case 163:
						{return new Symbol (sym.ID, yytext());}
					case -149:
						break;
					case 164:
						{return new Symbol (sym.ID, yytext());}
					case -150:
						break;
					case 165:
						{return new Symbol (sym.ID, yytext());}
					case -151:
						break;
					case 166:
						{return new Symbol (sym.ID, yytext());}
					case -152:
						break;
					case 167:
						{return new Symbol (sym.ID, yytext());}
					case -153:
						break;
					case 168:
						{return new Symbol (sym.ID, yytext());}
					case -154:
						break;
					case 169:
						{return new Symbol (sym.ID, yytext());}
					case -155:
						break;
					case 170:
						{return new Symbol (sym.ID, yytext());}
					case -156:
						break;
					case 171:
						{return new Symbol (sym.ID, yytext());}
					case -157:
						break;
					case 172:
						{return new Symbol (sym.ID, yytext());}
					case -158:
						break;
					case 173:
						{return new Symbol (sym.ID, yytext());}
					case -159:
						break;
					case 174:
						{return new Symbol (sym.ID, yytext());}
					case -160:
						break;
					case 175:
						{return new Symbol (sym.ID, yytext());}
					case -161:
						break;
					case 176:
						{return new Symbol (sym.ID, yytext());}
					case -162:
						break;
					case 177:
						{return new Symbol (sym.ID, yytext());}
					case -163:
						break;
					case 178:
						{return new Symbol (sym.ID, yytext());}
					case -164:
						break;
					case 179:
						{return new Symbol (sym.ID, yytext());}
					case -165:
						break;
					case 180:
						{return new Symbol (sym.ID, yytext());}
					case -166:
						break;
					case 181:
						{return new Symbol (sym.ID, yytext());}
					case -167:
						break;
					case 182:
						{return new Symbol (sym.ID, yytext());}
					case -168:
						break;
					case 183:
						{return new Symbol (sym.ID, yytext());}
					case -169:
						break;
					case 184:
						{return new Symbol (sym.ID, yytext());}
					case -170:
						break;
					case 185:
						{return new Symbol (sym.ID, yytext());}
					case -171:
						break;
					case 186:
						{return new Symbol (sym.ID, yytext());}
					case -172:
						break;
					case 187:
						{return new Symbol (sym.ID, yytext());}
					case -173:
						break;
					case 188:
						{return new Symbol (sym.ID, yytext());}
					case -174:
						break;
					case 189:
						{return new Symbol (sym.ID, yytext());}
					case -175:
						break;
					case 190:
						{return new Symbol (sym.ID, yytext());}
					case -176:
						break;
					case 191:
						{return new Symbol (sym.ID, yytext());}
					case -177:
						break;
					case 192:
						{return new Symbol (sym.ID, yytext());}
					case -178:
						break;
					case 193:
						{return new Symbol (sym.ID, yytext());}
					case -179:
						break;
					case 194:
						{return new Symbol (sym.ID, yytext());}
					case -180:
						break;
					case 195:
						{return new Symbol (sym.ID, yytext());}
					case -181:
						break;
					case 196:
						{return new Symbol (sym.ID, yytext());}
					case -182:
						break;
					case 197:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -183:
						break;
					case 198:
						{return new Symbol (sym.ID, yytext());}
					case -184:
						break;
					case 200:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -185:
						break;
					case 201:
						{return new Symbol (sym.ID, yytext());}
					case -186:
						break;
					case 202:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -187:
						break;
					case 203:
						{return new Symbol (sym.ID, yytext());}
					case -188:
						break;
					case 204:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -189:
						break;
					case 205:
						{return new Symbol (sym.ID, yytext());}
					case -190:
						break;
					case 206:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -191:
						break;
					case 207:
						{return new Symbol (sym.ID, yytext());}
					case -192:
						break;
					case 208:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -193:
						break;
					case 209:
						{return new Symbol (sym.ID, yytext());}
					case -194:
						break;
					case 210:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -195:
						break;
					case 211:
						{return new Symbol (sym.ID, yytext());}
					case -196:
						break;
					case 212:
						{return new Symbol (sym.ID, yytext());}
					case -197:
						break;
					case 213:
						{return new Symbol (sym.ID, yytext());}
					case -198:
						break;
					case 214:
						{return new Symbol (sym.ID, yytext());}
					case -199:
						break;
					case 215:
						{return new Symbol (sym.ID, yytext());}
					case -200:
						break;
					case 216:
						{return new Symbol (sym.ID, yytext());}
					case -201:
						break;
					case 217:
						{return new Symbol (sym.ID, yytext());}
					case -202:
						break;
					case 218:
						{return new Symbol (sym.ID, yytext());}
					case -203:
						break;
					case 219:
						{return new Symbol (sym.ID, yytext());}
					case -204:
						break;
					case 220:
						{return new Symbol (sym.ID, yytext());}
					case -205:
						break;
					case 221:
						{return new Symbol (sym.ID, yytext());}
					case -206:
						break;
					case 222:
						{return new Symbol (sym.ID, yytext());}
					case -207:
						break;
					case 223:
						{return new Symbol (sym.ID, yytext());}
					case -208:
						break;
					case 224:
						{return new Symbol (sym.ID, yytext());}
					case -209:
						break;
					case 225:
						{return new Symbol (sym.ID, yytext());}
					case -210:
						break;
					case 226:
						{return new Symbol (sym.ID, yytext());}
					case -211:
						break;
					case 227:
						{return new Symbol (sym.ID, yytext());}
					case -212:
						break;
					case 228:
						{return new Symbol (sym.ID, yytext());}
					case -213:
						break;
					case 229:
						{return new Symbol (sym.ID, yytext());}
					case -214:
						break;
					case 230:
						{return new Symbol (sym.ID, yytext());}
					case -215:
						break;
					case 231:
						{return new Symbol (sym.ID, yytext());}
					case -216:
						break;
					case 232:
						{return new Symbol (sym.ID, yytext());}
					case -217:
						break;
					case 233:
						{return new Symbol (sym.ID, yytext());}
					case -218:
						break;
					case 234:
						{return new Symbol (sym.ID, yytext());}
					case -219:
						break;
					case 235:
						{return new Symbol (sym.ID, yytext());}
					case -220:
						break;
					case 236:
						{return new Symbol (sym.ID, yytext());}
					case -221:
						break;
					case 237:
						{return new Symbol (sym.ID, yytext());}
					case -222:
						break;
					case 238:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -223:
						break;
					case 239:
						{return new Symbol (sym.ID, yytext());}
					case -224:
						break;
					case 240:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -225:
						break;
					case 241:
						{return new Symbol (sym.ID, yytext());}
					case -226:
						break;
					case 242:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -227:
						break;
					case 243:
						{return new Symbol (sym.ID, yytext());}
					case -228:
						break;
					case 244:
						{return new Symbol (sym.ID, yytext());}
					case -229:
						break;
					case 245:
						{return new Symbol (sym.ID, yytext());}
					case -230:
						break;
					case 246:
						{return new Symbol (sym.ID, yytext());}
					case -231:
						break;
					case 247:
						{return new Symbol (sym.ID, yytext());}
					case -232:
						break;
					case 248:
						{return new Symbol (sym.ID, yytext());}
					case -233:
						break;
					case 249:
						{return new Symbol (sym.ID, yytext());}
					case -234:
						break;
					case 250:
						{return new Symbol (sym.ID, yytext());}
					case -235:
						break;
					case 251:
						{return new Symbol (sym.ID, yytext());}
					case -236:
						break;
					case 252:
						{return new Symbol (sym.ID, yytext());}
					case -237:
						break;
					case 253:
						{return new Symbol (sym.ID, yytext());}
					case -238:
						break;
					case 254:
						{return new Symbol (sym.ID, yytext());}
					case -239:
						break;
					case 255:
						{return new Symbol (sym.ID, yytext());}
					case -240:
						break;
					case 256:
						{return new Symbol (sym.ID, yytext());}
					case -241:
						break;
					case 257:
						{return new Symbol (sym.ID, yytext());}
					case -242:
						break;
					case 258:
						{return new Symbol (sym.ID, yytext());}
					case -243:
						break;
					case 259:
						{return new Symbol (sym.ID, yytext());}
					case -244:
						break;
					case 260:
						{return new Symbol (sym.ID, yytext());}
					case -245:
						break;
					case 261:
						{return new Symbol (sym.ID, yytext());}
					case -246:
						break;
					case 262:
						{return new Symbol (sym.ID, yytext());}
					case -247:
						break;
					case 263:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -248:
						break;
					case 264:
						{return new Symbol (sym.ID, yytext());}
					case -249:
						break;
					case 265:
						{return new Symbol (sym.ID, yytext());}
					case -250:
						break;
					case 266:
						{return new Symbol (sym.ID, yytext());}
					case -251:
						break;
					case 267:
						{return new Symbol (sym.ID, yytext());}
					case -252:
						break;
					case 268:
						{return new Symbol (sym.ID, yytext());}
					case -253:
						break;
					case 269:
						{return new Symbol (sym.ID, yytext());}
					case -254:
						break;
					case 270:
						{return new Symbol (sym.ID, yytext());}
					case -255:
						break;
					case 271:
						{return new Symbol (sym.ID, yytext());}
					case -256:
						break;
					case 272:
						{return new Symbol (sym.ID, yytext());}
					case -257:
						break;
					case 273:
						{return new Symbol (sym.ID, yytext());}
					case -258:
						break;
					case 274:
						{return new Symbol (sym.ID, yytext());}
					case -259:
						break;
					case 275:
						{return new Symbol (sym.ID, yytext());}
					case -260:
						break;
					case 276:
						{return new Symbol (sym.ID, yytext());}
					case -261:
						break;
					case 277:
						{return new Symbol (sym.ID, yytext());}
					case -262:
						break;
					case 278:
						{return new Symbol (sym.ID, yytext());}
					case -263:
						break;
					case 279:
						{
					  throw new java.io.IOException(":" + yyline
					    + ": Unrecognised input: " +
					    yytext());
					}
					case -264:
						break;
					case 280:
						{return new Symbol (sym.ID, yytext());}
					case -265:
						break;
					case 281:
						{return new Symbol (sym.ID, yytext());}
					case -266:
						break;
					case 282:
						{return new Symbol (sym.ID, yytext());}
					case -267:
						break;
					case 283:
						{return new Symbol (sym.ID, yytext());}
					case -268:
						break;
					case 284:
						{return new Symbol (sym.ID, yytext());}
					case -269:
						break;
					case 285:
						{return new Symbol (sym.ID, yytext());}
					case -270:
						break;
					case 286:
						{return new Symbol (sym.ID, yytext());}
					case -271:
						break;
					case 287:
						{return new Symbol (sym.ID, yytext());}
					case -272:
						break;
					case 288:
						{return new Symbol (sym.ID, yytext());}
					case -273:
						break;
					case 289:
						{return new Symbol (sym.ID, yytext());}
					case -274:
						break;
					case 290:
						{return new Symbol (sym.ID, yytext());}
					case -275:
						break;
					case 291:
						{return new Symbol (sym.ID, yytext());}
					case -276:
						break;
					case 292:
						{return new Symbol (sym.ID, yytext());}
					case -277:
						break;
					case 293:
						{return new Symbol (sym.ID, yytext());}
					case -278:
						break;
					case 294:
						{return new Symbol (sym.ID, yytext());}
					case -279:
						break;
					case 295:
						{return new Symbol (sym.ID, yytext());}
					case -280:
						break;
					case 296:
						{return new Symbol (sym.ID, yytext());}
					case -281:
						break;
					case 297:
						{return new Symbol (sym.ID, yytext());}
					case -282:
						break;
					case 298:
						{return new Symbol (sym.ID, yytext());}
					case -283:
						break;
					case 299:
						{return new Symbol (sym.ID, yytext());}
					case -284:
						break;
					case 300:
						{return new Symbol (sym.ID, yytext());}
					case -285:
						break;
					case 301:
						{return new Symbol (sym.ID, yytext());}
					case -286:
						break;
					case 302:
						{return new Symbol (sym.ID, yytext());}
					case -287:
						break;
					case 303:
						{return new Symbol (sym.ID, yytext());}
					case -288:
						break;
					case 304:
						{return new Symbol (sym.ID, yytext());}
					case -289:
						break;
					case 305:
						{return new Symbol (sym.ID, yytext());}
					case -290:
						break;
					case 306:
						{return new Symbol (sym.ID, yytext());}
					case -291:
						break;
					case 307:
						{return new Symbol (sym.ID, yytext());}
					case -292:
						break;
					case 308:
						{return new Symbol (sym.ID, yytext());}
					case -293:
						break;
					case 309:
						{return new Symbol (sym.ID, yytext());}
					case -294:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
