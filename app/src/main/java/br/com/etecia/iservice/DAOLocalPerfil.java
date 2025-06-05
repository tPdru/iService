package br.com.etecia.iservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

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
    public long inserirPerfil(ObjPerfil perfil) {

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

        return id;
    }

    /**
     * Método para listar todas os PERFILS do banco.
     * Retorna uma Lista de ObjPerfil.
     */
    public List<ObjPerfil> readPerfil() {

        // Instanciando a lista que vai ser retornada
        List<ObjPerfil> lista = new ArrayList<>();

        // Obtém o banco no modo de leitura.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define as colunas que queremos consultar.
        String[] colunas = {
                dbHelper.COLUMN_ID_PERFIL,
                dbHelper.COLUMN_NOME_PERFIL,
                dbHelper.COLUMN_IMG_PERFIL,
                dbHelper.COLUMN_TEM_LOJA_PERFIL,
                dbHelper.COLUMN_SENHA_PERFIL,
                dbHelper.COLUMN_USUARIO_PERFIL,
                dbHelper.COLUMN_EMAIL_PERFIL,
                dbHelper.COLUMN_CELULAR_PERFIL

        };

        // Faz a consulta no banco.
        Cursor cursor = db.query(
                DbHelper.TABLE_PERFIL, // tabela
                colunas,                 // colunas
                null,                    // where
                null,                    // whereArgs
                null,                    // groupBy
                null,                    // having
                null                     // orderBy
        );

        // Percorre o Cursor e cria objetos ObjServico.
        if (cursor.moveToFirst()) {

            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_ID_PERFIL));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_NOME_PERFIL));
                String usuario =  cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_USUARIO_PERFIL));
                int temLoja = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TEM_LOJA_PERFIL));
                String celular = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_CELULAR_PERFIL));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_EMAIL_PERFIL));
                String senha = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_SENHA_PERFIL));
                String img = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_IMG_PERFIL));

                //Objeto Loja vazio
                ObjPerfil perfil = new ObjPerfil();

                // setando as informaçoes
                perfil.setCodigo(id);
                perfil.setNome(nome);
                perfil.setUsuario(usuario);
                perfil.setTemLoja(temLoja == 1 ? true:false);
                perfil.setCelular(celular);
                perfil.setEmail(email);
                perfil.setSenha(senha);

                //Adicionando a lista
                lista.add(perfil);

            }while (cursor.moveToNext());
        }

        // Fecha o cursor e o banco.
        cursor.close();
        db.close();

        return lista;  // Retorna a lista

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
