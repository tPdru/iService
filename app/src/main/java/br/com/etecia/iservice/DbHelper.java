package br.com.etecia.iservice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;




public class DbHelper extends SQLiteOpenHelper {

    // Nome do Banco de Dados
    private static final String DATABASE_NAME = "dbQuackWorks.db";

    // Versão do Banco (aumente se fizer alterações na estrutura)
    private static final int DATABASE_VERSION = 1;



    // Nome das tabelas
    public static final String TABLE_PERFIL = "perfil";
    public static final String TABLE_ENDERECO = "endereco";
    public static final String TABLE_LOJA = "loja";
    public static final String TABLE_SERVICO = "servico";

    /**----------------------------- Colunas das tabelas-------------------------------**/
    //Coluna ------------------------------PERFIl------------------------------
    public static final String COLUMN_ID_PERFIL = "id_perfil";
    public static final String COLUMN_NOME_PERFIL = "nome_perfil";
    public static final String COLUMN_IMG_PERFIL = "img_perfil";
    public static final String COLUMN_TEM_LOJA_PERFIL = "tem_loja_perfil";
    public static final String COLUMN_EMAIL_PERFIL = "email_perfil";
    public static final String COLUMN_SENHA_PERFIL = "senha_perfil";
    public static final String COLUMN_USUARIO_PERFIL = "usuario_perfil";
    public static final String COLUMN_CELULAR_PERFIL = "celular_perfil";

    //Coluna ------------------------------LOJA------------------------------
    public static final String COLUMN_ID_LOJA = "id_loja";
    public static final String COLUMN_ID_PERFIL_FK = "id_perfil_fk";
    public static final String COLUMN_NOME_LOJA = "nome_loja";
    public static final String COLUMN_IMG_LOJA = "img_loja";
    public static final String COLUMN_CPF_CNPJ_LOJA = "cpf_cnpj_loja";
    public static final String COLUMN_TEM_END_LOJA = "tem_end_loja";
    public static final String COLUMN_TEM_SERV_LOJA = "tem_serv_loja";
    public static final String COLUMN_DESCRICAO_LOJA = "descricao_loja";

    //Coluna ------------------------------SERVIÇO------------------------------
    public static final String COLUMN_ID_SERV = "id_service";
    public static final String COLUMN_ID_LOJA_FK = "id_loja_fk";
    public static final String COLUMN_IMG_SERV = "img_service";
    public static final String COLUMN_DESCRICAO_SERV = "descricao_service";
    public static final String COLUMN_NOME_SERV = "nome_service";
    public static final String COLUMN_VALOR_SERV = "valor_service";

    //Coluna ------------------------------ENDEREÇO------------------------------
    public static final String COLUMN_ID_ENDERECO = "id_endereco";
    public static final String COLUMN_ID_LOJA_END_FK = "id_loja_end_fk";
    public static final String COLUMN_CEP_ENDERECO = "cep_endereco";
    public static final String COLUMN_ESTADO_ENDERECO = "estado_endereco";
    public static final String COLUMN_CIDADE_ENDERECO = "ciadade_endereco";
    public static final String COLUMN_BAIRRO_ENDERECO = "bairro_endereco";
    public static final String COLUMN_RUA_ENDERECO = "rua_endereco";
    public static final String COLUMN_NUMERO_ENDERECO = "numero_endereco";
    public static final String COLUMN_COMPLEMENTO_ENDERECO = "cromplemento_endereco";

    /**------------------------------ Fim Colunas ------------------------------**/


    /**------------------------------ SQL Criação de tabelas ------------------------------**/
    //Tabela ------------------------------PERFIl------------------------------
    private static final String TABLE_CREATE_PERFIL =
            "CREATE TABLE " + TABLE_PERFIL + " (" +
                    COLUMN_ID_PERFIL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOME_PERFIL + " TEXT, " +
                    COLUMN_IMG_PERFIL + " BLOB, " +
                    COLUMN_TEM_LOJA_PERFIL + " INTEGER, " +
                    COLUMN_EMAIL_PERFIL + " TEXT, " +
                    COLUMN_SENHA_PERFIL + " TEXT, " +
                    COLUMN_USUARIO_PERFIL + " TEXT, " +
                    COLUMN_CELULAR_PERFIL + " TEXT" +
                    ");";

    //Tabela ------------------------------LOJA------------------------------
    private static final String TABLE_CREATE_LOJA =
            "CREATE TABLE " + TABLE_LOJA + " (" +
                    COLUMN_ID_LOJA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ID_PERFIL_FK + " INTEGER, " +
                    COLUMN_NOME_LOJA + " TEXT, " +
                    COLUMN_IMG_LOJA + "     BLOB, " +
                    COLUMN_CPF_CNPJ_LOJA + " TEXT, " +
                    COLUMN_TEM_END_LOJA + " INTEGER, " +
                    COLUMN_TEM_SERV_LOJA + " INTEGER, " +
                    COLUMN_DESCRICAO_LOJA + " TEXT, " +
                    "FOREIGN KEY (" + COLUMN_ID_PERFIL_FK + ") REFERENCES " + TABLE_PERFIL + "(" + COLUMN_ID_PERFIL + ")" +
                    ");";

    //Tabela ------------------------------SERVICE------------------------------
    private static final String TABLE_CREATE_SERVICO =
            "CREATE TABLE " + TABLE_SERVICO + " (" +
                    COLUMN_ID_SERV + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ID_LOJA_FK + " INTEGER, " +
                    COLUMN_IMG_SERV + " BLOB, " +
                    COLUMN_DESCRICAO_SERV + " TEXT, " +
                    COLUMN_NOME_SERV + " TEXT, " +
                    COLUMN_VALOR_SERV + " REAL, " +
                    "FOREIGN KEY (" + COLUMN_ID_LOJA_FK + ") REFERENCES " + TABLE_LOJA + "(" + COLUMN_ID_LOJA + ")" +
                    ");";

    //Tabela ------------------------------ENDERECO------------------------------
    private static final String TABLE_CREATE_ENDERECO =
            "CREATE TABLE " + TABLE_ENDERECO + " (" +
                    COLUMN_ID_ENDERECO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ID_LOJA_END_FK + " INTEGER, " +
                    COLUMN_CEP_ENDERECO + " TEXT, " +
                    COLUMN_ESTADO_ENDERECO + " TEXT, " +
                    COLUMN_CIDADE_ENDERECO + " TEXT, " +
                    COLUMN_BAIRRO_ENDERECO + " TEXT, " +
                    COLUMN_RUA_ENDERECO + " TEXT, " +
                    COLUMN_NUMERO_ENDERECO + " TEXT, " +
                    COLUMN_COMPLEMENTO_ENDERECO + " TEXT, " +
                    "FOREIGN KEY (" + COLUMN_ID_LOJA_END_FK + ") REFERENCES " + TABLE_LOJA + "(" + COLUMN_ID_LOJA + ")" +
                    ");";

    /**------------------------------ Fim SQL ------------------------------**/




    /** Construtor Obrigatorio */
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Método chamado na criação do banco.
     * Criamos a tabela aqui.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criação das tabelas
        db.execSQL(TABLE_CREATE_PERFIL);
        db.execSQL(TABLE_CREATE_LOJA);
        db.execSQL(TABLE_CREATE_SERVICO);
        db.execSQL(TABLE_CREATE_ENDERECO);
    }

    /**
     * Método chamado quando a versão do banco muda.
     * Aqui podemos atualizar a estrutura.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Exclui a tabelas se existir
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERFIL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOJA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENDERECO);
        // Criar novamente
        onCreate(db);

    }

    /**
     * Método que para passar configurações
     */
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        db.setForeignKeyConstraintsEnabled(true);// Ativa o uso de chave estrangeiras
    }
}
