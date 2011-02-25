package br.com.jera.botaoteca.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class OpenHelper extends SQLiteOpenHelper {

	public OpenHelper(Context context) {
		super(context, DataHelper.getDatabaseName(), null, DataHelper.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(DataHelper.LOG_TAG, "Creating database");
		db.execSQL(DataHelper.CREATE_SQL);
		db.setVersion(DataHelper.DATABASE_VERSION);
		populateDatabase(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.w(DataHelper.LOG_TAG, "Upgrading database, this will drop tables and recreate.");
		db.execSQL(DataHelper.DROP_SQL );
		
		Log.i(DataHelper.LOG_TAG, "Creating database");
		db.execSQL(DataHelper.CREATE_SQL);
		db.setVersion(newVersion);
		populateDatabase(db);

	}
	
	  private void populateDatabase(SQLiteDatabase db) {
		db.beginTransaction();
		db.execSQL("INSERT INTO sounds VALUES ('pedrochip.mp3','Me dá meu Chipe',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('vuvuzela.mp3','Vuvuzela',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('ladygaga.mp3','Tá ouvindo ?',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('profissional.mp3','Profissional do Sequicu',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('dacarrinhonao.mp3','Da carrin não',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('taffarel.mp3','Taffarel',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('dungaburro.mp3','Dunga Burro',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('para.mp3','Para Gatchenho',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('adogo.mp3','Adogo',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('susto.mp3','Ai que Susto',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('garotalaje.mp3','Garota da Laje',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('bruxa.mp3','Parangaricu tirimirruaro',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('nathan.mp3','Cantando no Chuveiro',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('herbert.mp3','Herbert Richards',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('velha.mp3','Velha Safada',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('grilo.mp3','Grilo',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('atras.mp3','Esta que esta aqui atras',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('faustao.mp3','O Loco meu',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('pobreza.mp3','Pobreza Pega',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('quercia.mp3','Mentiroso e Caluniador',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('bjomeliga.mp3','Beijo me liga',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('sacanagem.mp3','Gosto de Sacanagem',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('gary.mp3','Gary Coleman',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('imburning.mp3','Bixa Muda',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('disco.mp3','Ja chegou o disco voador',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('lidio.mp3','Lidio Fresco boiola',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('ponto.mp3','Banheira do Gugu',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('dunga.mp3','Entrevista Dunga',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('aiqloucura.mp3','Narcisa Tamborindeguy',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('mamonas.mp3','Mamonas Assassinas',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('treta.mp3','Eu estou sentindo uma treta',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('garotao.mp3','Garotão',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('funkjoelsantana.mp3','Joel Santana',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('marta.mp3','Relaxa e Goza',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('putafaltadesacanagem.mp3','Puta falta de sacanagem',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('aii.mp3','Richarlyson',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('antonionunes.mp3','Antonio Nunes',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('treplicantes.mp3','Treplicantes',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('hmboiola.mp3','Hm Boiola',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('zina.mp3','Topo porque não',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('bruxao.mp3','Nois que voa bruxão',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('aisim.mp3','Zagallo',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('petrefiolismo.mp3','Fisica do Petrefiolismo',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('chinelo.mp3','Cade o chinelo',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('serracomedor.mp3','Serra Comedor',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('tiririca.mp3','Vote em Tiririca',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('sexta.mp3','Sexta Feira',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('collor.mp3','Fernando Collor',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('tatata.mp3','Ta ta ta',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES('tudumpa.mp3','Tudum pa',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('tetra.mp3','Tetra',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('xingarnotwitter.mp3','Xingar no Twitter',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('lambdanerd.mp3','Jovem Nerd',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('agulha.mp3','Agulha',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('vaivaivainanana.mp3','Vai vai vai vai',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('lula.mp3','Presidente Lula',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('vempraca.mp3','Vem pra ca',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('nossa.mp3','Nooossaaa',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('significa.mp3','Ronnie Von - Significa',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('valenada.mp3','Voce não vale nada',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('pantera.mp3','Tapa na Pantera',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('danca.mp3','Danca Gatinho',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('claudia.mp3','Claudia',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('arveres.mp3','As arveres somos nozes',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('creu.mp3','Creu',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('plantao.mp3','Plantão da Globo',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('atoron.mp3','Adoro Perigo',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('fistaile.mp3','Eh Fistaile',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('aluguel.mp3','Pague o Aluguel',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('uivo.mp3','Uivo',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('embolotei.mp3','Quase Embolotei',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('respeito.mp3','Com Respeito',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('uepa.mp3','Uepa',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('malandro.mp3','Pegadinha do Malandro',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('calaboca.mp3','CALA BOCA GALVÃO',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('maluf.mp3','Paulo Maluf',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('vanessao.mp3','Vanessão',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('bichona.mp3','Bichona',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('lourdes.mp3','Dona Lourdes',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('ciladabino.mp3','Eh uma cilada Bino',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('vera.mp3','Vera Verão',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('cacilds.mp3','Ih Cacilds',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('frango.mp3','Que Frango',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('felipeneto.mp3','Felipe Neto',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('dilma.mp3','Oi Dilma',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('euvi.mp3','Dilma Viu',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('naoentendi.mp3','Não Entendi',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('companheiro.mp3','Paulinho Rola',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('picapau.mp3','Picapau',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('ritmodefesta.mp3','Ritmo de Festa',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('bicha.mp3','Laila Dominique',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('grande.mp3','Mais Nois eh ruim',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('rica.mp3','Eu sou Rica',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('ronaldo.mp3','Ronaldo',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('fisica.mp3','A Fisica',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('oifernanda.mp3','Oooii Fernanda',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('naosei.mp3','So sei que foi assim',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('hojenao.mp3','Cleber Machado',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('tropa.mp3','Tropa de Elite',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('beijo.mp3','French Kiss',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('muleque.mp3','Ah muleque',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('pele.mp3','Filme do Pele',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('emofiuk.mp3','Fiuk',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('tutuquinho.mp3','Tutuquinho',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('rebolation.mp3','Rebolation',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('presente.mp3','Marcus Tramontin',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('romario.mp3','Romário',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('arnaldo.mp3','A Regra é Clara',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('mussum.mp3','Suco de Cevads',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('nojento.mp3','I Nojento',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('meioambiente.mp3','Dilma e a Natureza',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('jeremias.mp3','Jeremias',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('courovelho.mp3','Couro velho',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('aloca.mp3','Serginho',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('travesti.mp3','Travesti',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('colosso.mp3','Ta na Mesa',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('coracaozinho.mp3','S2 S2',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('brasil.mp3','Brasil sil sil',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('salomao.mp3','Felipe Melo',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES ('lasier.mp3','Lasier Martins',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('bambu.mp3','E o bambu ?',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('leonaldo.mp3','Leonaldo Gomes',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('burro.mp3','Ai que burro',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('antibes.mp3','Eu Tenho Horror a Pobre',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('bola.mp3','Bola',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('milton.mp3','Que Beleza',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('pepe.mp3','Pepe ja tirei a vela',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('quebom.mp3','Que Bom',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('klimber.mp3','Joseph Klimber',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('boanoite.mp3','Boa Noite',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('iche.mp3','Sanduiche iche',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('galvao.mp3','Haja coração',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('pinto.mp3','Cade meu pinto',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('oiadriano.mp3','Adriano',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('vemca.mp3','Vem ca te conheco',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('jagger.mp3','Mick Jagger',1,'BLUE')");
		db.setTransactionSuccessful();
		db.endTransaction();
	    }

}
