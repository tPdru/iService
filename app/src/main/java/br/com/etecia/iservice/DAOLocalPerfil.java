package br.com.etecia.iservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAOLocalPerfil {

    private DbHelper dbHelper;

    /**
     * Construtor recebe o Context e cria o DbHelper.
     * Assim conseguimos acessar o banco de dados.
     */
    public DAOLocalPerfil(Context context) {
        dbHelper = new DbHelper(context);
    }

    /**
     * Método para inserir um novo PERFIL no banco de dados.
     * Recebe um objeto ObjPerfil como parâmetro.
     */
    public boolean inserirPerfil(ObjPerfil perfil) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Cria um ContentValues para mapear as colunas aos valores
        ContentValues values = new ContentValues();

        //Colocando os valores
        values.put(dbHelper.COLUMN_NOME_PERFIL, perfil.getNome());
        values.put(dbHelper.COLUMN_EMAIL_PERFIL, perfil.getEmail());
        values.put(dbHelper.COLUMN_USUARIO_PERFIL, perfil.getUsuario());
        values.put(dbHelper.COLUMN_SENHA_PERFIL, perfil.getSenha());
        values.put(dbHelper.COLUMN_IMG_PERFIL, perfil.getFotoUsuario());
        values.put(dbHelper.COLUMN_TEM_LOJA_PERFIL, perfil.isTemLoja());
        values.put(dbHelper.COLUMN_CELULAR_PERFIL, perfil.getCelular());

        // Insere no banco e retorna o ID gerado.
        long id = db.insert(DbHelper.TABLE_PERFIL, null, values);

        // Fecha o banco após operação.
        db.close();

        // True em caso de sucesso
        if ( id != -1 ) return true;

        return false;
    }



    /**
     * Método para deletar um PERFIL do banco de dados.
     * Recebe o ID do PERFIL a ser excluído.
     */
    public boolean deletePerfil(int codigo) {

        // Obtém o banco no modo de escrita.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String qual = DbHelper.COLUMN_ID_PERFIL + " = ?";
        String[] qualArgumentos = {String.valueOf(codigo)};

        // Executa o delete.
        int linhasAfetadas = db.delete(DbHelper.TABLE_PERFIL, qual, qualArgumentos);

        // Fecha o banco após operação.
        db.close();

        //Retora true caso a exclusão tenha sido um sucesso
        if ( linhasAfetadas > 0 ) return true;

        return false;
    }



}
