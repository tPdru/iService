package br.com.etecia.iservice;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    ControllerMaster contMaster = ControllerMaster.getControllerMaster();
    ImageView imgLojaInicial;
    byte[] imageBytes;



    //Banco local
    DAOLocalPerfil daoLocalPerfil;
    DAOLocalLoja daoLocalLoja;
    DAOLocalEndereco daoLocalEndereco;
    DAOLocalService daoLocalService;

    private byte[] imageViewToByte(ImageView imgLojaInicial) {
        // Pega o drawable (imagem) do ImageView e o converte para Bitmap
        Bitmap bitmap = ((BitmapDrawable) imgLojaInicial.getDrawable()).getBitmap();

        // Cria um fluxo de bytes na memória para armazenar a imagem comprimida
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        // Comprime o bitmap em formato PNG com qualidade 100% e escreve no stream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        // Retorna o array de bytes com os dados da imagem comprimida
        return stream.toByteArray();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash_layout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        imgLojaInicial = findViewById(R.id.imgLojaInicial);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Definindo que o app não possui conta logada no inicio
                contMaster.setLoginOn(false);

                //Banco local
                // Instancias
                daoLocalPerfil = new DAOLocalPerfil(getApplicationContext());
                daoLocalLoja = new DAOLocalLoja(getApplicationContext());
                daoLocalEndereco = new DAOLocalEndereco(getApplicationContext());
                daoLocalService = new DAOLocalService(getApplicationContext());

                List<ObjPerfil> lista = new ArrayList<>(daoLocalPerfil.readPerfil());

                if (lista.size() < 1) {
                    adiconar();
                    adicionarAssistencia();
                    adicionarBarbearia();
                    adicionarLavanderia();
                    adicionarPetShop();
                    adicionarSalaoBeleza();
                }


                // Coloca os perfis na lista do controleMaster
                /*contMaster.setCarregarListaPerfil(daoLocalPerfil.readPerfil());*/

                //contMaster.carregarLojas();
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        }, 2000);

    }

    public void adiconar() {

        ObjPerfil perfil = new ObjPerfil();
        ObjCardLoja loja = new ObjCardLoja();
        ObjEndereco endereco = new ObjEndereco();

        perfil.setNome("Joana Pereira");
        perfil.setEmail("joana.oficina@exemplo.com");
        perfil.setUsuario("JoanaOficina");
        perfil.setSenha("senhaSegura123");
        perfil.setTemLoja(true);
        perfil.setCelular("11999998888");
        perfil.setCodigo(daoLocalPerfil.inserirPerfil(perfil));

        imgLojaInicial.setImageResource(R.drawable.foto_imagem);
        imageBytes = imageViewToByte(imgLojaInicial);

        loja.setNomeLoja("Oficina da Joana");
        loja.setImgLoja(imageBytes);
        loja.setCpfCnpj("12345678000190");
        loja.setDescricao("Serviços automotivos especializados");
        loja.setTemEndereco(true);
        loja.setTemServicos(true);
        loja.setCodUsuario(perfil.getCodigo());

        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

        endereco.setCepCnpj(12345678);
        endereco.setEstado("SP");
        endereco.setCidade("São Paulo");
        endereco.setBairro("Vila Mecânica");
        endereco.setRua("Rua das Engrenagens");
        endereco.setNumero(42);
        endereco.setComplemento("Ao lado do posto");
        endereco.setCodigoLoja(loja.getCodigLoja());

        endereco.setCodigo(daoLocalEndereco.inserirEndereco(endereco));

        // Serviço 1
        ObjCardServicoPp ser = new ObjCardServicoPp();
        ser.setTxtNomeServicoPp("Troca de óleo");
        ser.setTxtDetalhesServicoPp("Troca de óleo sintético e verificação de filtro.");
        ser.setTxtValorServicoPp("120,00");
        ser.setImgServicoPp(imageBytes);
        ser.setCodigoLoja(loja.getCodigLoja());
        ser.setCodigo(daoLocalService.inserirService(ser));

        // Serviço 2
        ser = new ObjCardServicoPp();
        ser.setTxtNomeServicoPp("Alinhamento e balanceamento");
        ser.setTxtDetalhesServicoPp("Ajuste da suspensão e balanceamento das rodas.");
        ser.setTxtValorServicoPp("90,00");
        ser.setImgServicoPp(imageBytes);
        ser.setCodigoLoja(loja.getCodigLoja());
        ser.setCodigo(daoLocalService.inserirService(ser));

        // Serviço 3
        ser = new ObjCardServicoPp();
        ser.setTxtNomeServicoPp("Revisão completa");
        ser.setTxtDetalhesServicoPp("Revisão de 40 itens + diagnóstico eletrônico.");
        ser.setTxtValorServicoPp("350,00");
        ser.setImgServicoPp(imageBytes);
        ser.setCodigoLoja(loja.getCodigLoja());
        ser.setCodigo(daoLocalService.inserirService(ser));

        // Serviço 4
        ser = new ObjCardServicoPp();
        ser.setTxtNomeServicoPp("Troca de pastilhas de freio");
        ser.setTxtDetalhesServicoPp("Troca das pastilhas dianteiras com peças inclusas.");
        ser.setTxtValorServicoPp("180,00");
        ser.setImgServicoPp(imageBytes);
        ser.setCodigoLoja(loja.getCodigLoja());
        ser.setCodigo(daoLocalService.inserirService(ser));

        // Serviço 5
        ser = new ObjCardServicoPp();
        ser.setTxtNomeServicoPp("Higienização do ar-condicionado");
        ser.setTxtDetalhesServicoPp("Limpeza profunda do sistema de ventilação.");
        ser.setTxtValorServicoPp("100,00");
        ser.setImgServicoPp(imageBytes);
        ser.setCodigoLoja(loja.getCodigLoja());
        ser.setCodigo(daoLocalService.inserirService(ser));
    }

    private void adicionarLavanderia() {
        ObjPerfil perfil = new ObjPerfil();
        ObjCardLoja loja = new ObjCardLoja();
        ObjEndereco endereco = new ObjEndereco();

        perfil.setNome("Carlos Lima");
        perfil.setEmail("carlos.lavanderia@exemplo.com");
        perfil.setUsuario("CarlosClean");
        perfil.setSenha("limpeza2024");
        perfil.setTemLoja(true);
        perfil.setCelular("21988887777");
        perfil.setCodigo(daoLocalPerfil.inserirPerfil(perfil));

        imgLojaInicial.setImageResource(R.drawable.foto_imagem);
        imageBytes = imageViewToByte(imgLojaInicial);

        loja.setNomeLoja("Lavanderia Ponto Certo");
        loja.setImgLoja(imageBytes);
        loja.setCpfCnpj("43900876000111");
        loja.setDescricao("Lavagem de roupas, edredons e peças especiais.");
        loja.setTemEndereco(true);
        loja.setTemServicos(true);
        loja.setCodUsuario(perfil.getCodigo());
        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

        endereco.setCepCnpj(12345678);
        endereco.setEstado("RJ");
        endereco.setCidade("Rio de Janeiro");
        endereco.setBairro("Tijuca");
        endereco.setRua("Rua das Águas Limpas");
        endereco.setNumero(101);
        endereco.setComplemento("Ao lado do supermercado");
        endereco.setCodigoLoja(loja.getCodigLoja());
        endereco.setCodigo(daoLocalEndereco.inserirEndereco(endereco));

        String[] nomes = {"Lavagem comum", "Lavagem de edredom", "Lavagem a seco", "Passadoria", "Combo mensal"};
        String[] detalhes = {
                "Lavagem e secagem de roupas do dia a dia.",
                "Lavagem especializada para peças grandes.",
                "Limpeza de roupas delicadas sem água.",
                "Passamos roupas com vapor profissional.",
                "Pacote mensal com 20 peças incluídas."
        };
        String[] valores = {"25,00", "45,00", "60,00", "3,00/unid", "200,00"};

        for (int i = 0; i < nomes.length; i++) {
            ObjCardServicoPp ser = new ObjCardServicoPp();
            ser.setTxtNomeServicoPp(nomes[i]);
            ser.setTxtDetalhesServicoPp(detalhes[i]);
            ser.setTxtValorServicoPp(valores[i]);
            ser.setImgServicoPp(imageBytes);
            ser.setCodigoLoja(loja.getCodigLoja());
            ser.setCodigo(daoLocalService.inserirService(ser));
        }
    }

    private void adicionarBarbearia() {
        ObjPerfil perfil = new ObjPerfil();
        ObjCardLoja loja = new ObjCardLoja();
        ObjEndereco endereco = new ObjEndereco();

        perfil.setNome("Felipe Moura");
        perfil.setEmail("felipe.barbearia@exemplo.com");
        perfil.setUsuario("BarberFelipe");
        perfil.setSenha("corte123");
        perfil.setTemLoja(true);
        perfil.setCelular("11955663322");
        perfil.setCodigo(daoLocalPerfil.inserirPerfil(perfil));

        imgLojaInicial.setImageResource(R.drawable.foto_imagem);
        imageBytes = imageViewToByte(imgLojaInicial);

        loja.setNomeLoja("Barbearia Estilo Fino");
        loja.setImgLoja(imageBytes);
        loja.setCpfCnpj("78120983000155");
        loja.setDescricao("Cortes modernos, barba e cuidados masculinos.");
        loja.setTemEndereco(true);
        loja.setTemServicos(true);
        loja.setCodUsuario(perfil.getCodigo());
        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

        endereco.setCepCnpj(87654321);
        endereco.setEstado("SP");
        endereco.setCidade("Campinas");
        endereco.setBairro("Centro");
        endereco.setRua("Av. dos Cabeleireiros");
        endereco.setNumero(88);
        endereco.setComplemento("Sala 5");
        endereco.setCodigoLoja(loja.getCodigLoja());
        endereco.setCodigo(daoLocalEndereco.inserirEndereco(endereco));

        String[] nomes = {"Corte masculino", "Barba na navalha", "Combo corte + barba", "Sobrancelha", "Hidratação capilar"};
        String[] detalhes = {
                "Cortes modernos e clássicos sob medida.",
                "Modelagem + toalha quente.",
                "Combo com desconto para os dois serviços.",
                "Design com navalha.",
                "Tratamento nutritivo para cabelos masculinos."
        };
        String[] valores = {"35,00", "25,00", "55,00", "10,00", "20,00"};

        for (int i = 0; i < nomes.length; i++) {
            ObjCardServicoPp ser = new ObjCardServicoPp();
            ser.setTxtNomeServicoPp(nomes[i]);
            ser.setTxtDetalhesServicoPp(detalhes[i]);
            ser.setTxtValorServicoPp(valores[i]);
            ser.setImgServicoPp(imageBytes);
            ser.setCodigoLoja(loja.getCodigLoja());
            ser.setCodigo(daoLocalService.inserirService(ser));
        }
    }

    private void adicionarPetShop() {
        ObjPerfil perfil = new ObjPerfil();
        ObjCardLoja loja = new ObjCardLoja();
        ObjEndereco endereco = new ObjEndereco();

        perfil.setNome("Juliana Torres");
        perfil.setEmail("juliana.petshop@exemplo.com");
        perfil.setUsuario("JuliPet");
        perfil.setSenha("pet2024");
        perfil.setTemLoja(true);
        perfil.setCelular("11944556688");
        perfil.setCodigo(daoLocalPerfil.inserirPerfil(perfil));

        imgLojaInicial.setImageResource(R.drawable.foto_imagem);
        imageBytes = imageViewToByte(imgLojaInicial);

        loja.setNomeLoja("Pet Vida Feliz");
        loja.setImgLoja(imageBytes);
        loja.setCpfCnpj("90811230000177");
        loja.setDescricao("Cuidando do seu pet com carinho e qualidade.");
        loja.setTemEndereco(true);
        loja.setTemServicos(true);
        loja.setCodUsuario(perfil.getCodigo());
        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

        endereco.setCepCnpj(13579246);
        endereco.setEstado("SP");
        endereco.setCidade("Sorocaba");
        endereco.setBairro("Jardim Europa");
        endereco.setRua("Rua dos Animais Felizes");
        endereco.setNumero(45);
        endereco.setComplemento("");
        endereco.setCodigoLoja(loja.getCodigLoja());
        endereco.setCodigo(daoLocalEndereco.inserirEndereco(endereco));

        String[] nomes = {"Banho pequeno porte", "Tosa higiênica", "Consulta veterinária", "Vacinação V8", "Banho + Tosa completa"};
        String[] detalhes = {
                "Banho com produtos específicos e secagem.",
                "Tosa das áreas íntimas e patas.",
                "Consulta clínica com veterinário parceiro.",
                "Aplicação da vacina V8 para cães.",
                "Pacote completo para pets de até 15kg."
        };
        String[] valores = {"35,00", "25,00", "100,00", "90,00", "70,00"};

        for (int i = 0; i < nomes.length; i++) {
            ObjCardServicoPp ser = new ObjCardServicoPp();
            ser.setTxtNomeServicoPp(nomes[i]);
            ser.setTxtDetalhesServicoPp(detalhes[i]);
            ser.setTxtValorServicoPp(valores[i]);
            ser.setImgServicoPp(imageBytes);
            ser.setCodigoLoja(loja.getCodigLoja());
            ser.setCodigo(daoLocalService.inserirService(ser));
        }
    }

    private void adicionarSalaoBeleza() {
        ObjPerfil perfil = new ObjPerfil();
        ObjCardLoja loja = new ObjCardLoja();
        ObjEndereco endereco = new ObjEndereco();

        perfil.setNome("Renata Silva");
        perfil.setEmail("renata.beleza@exemplo.com");
        perfil.setUsuario("ReBeleza");
        perfil.setSenha("beleza123");
        perfil.setTemLoja(true);
        perfil.setCelular("2199998888");
        perfil.setCodigo(daoLocalPerfil.inserirPerfil(perfil));

        imgLojaInicial.setImageResource(R.drawable.foto_imagem);
        imageBytes = imageViewToByte(imgLojaInicial);

        loja.setNomeLoja("Estética & Beleza");
        loja.setImgLoja(imageBytes);
        loja.setCpfCnpj("30491823000166");
        loja.setDescricao("Transformando sua beleza todos os dias.");
        loja.setTemEndereco(true);
        loja.setTemServicos(true);
        loja.setCodUsuario(perfil.getCodigo());
        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

        endereco.setCepCnpj(11223344);
        endereco.setEstado("MG");
        endereco.setCidade("Belo Horizonte");
        endereco.setBairro("Savassi");
        endereco.setRua("Rua das Estilistas");
        endereco.setNumero(234);
        endereco.setComplemento("Loja 4");
        endereco.setCodigoLoja(loja.getCodigLoja());
        endereco.setCodigo(daoLocalEndereco.inserirEndereco(endereco));

        String[] nomes = {"Corte feminino", "Escova progressiva", "Pintura completa", "Hidratação profunda", "Sobrancelha com henna"};
        String[] detalhes = {
                "Corte com visagismo personalizado.",
                "Tratamento para alisar e reduzir volume.",
                "Coloração com produtos profissionais.",
                "Hidratação com máscara nutritiva.",
                "Modelagem + aplicação de henna."
        };
        String[] valores = {"50,00", "120,00", "90,00", "40,00", "30,00"};

        for (int i = 0; i < nomes.length; i++) {
            ObjCardServicoPp ser = new ObjCardServicoPp();
            ser.setTxtNomeServicoPp(nomes[i]);
            ser.setTxtDetalhesServicoPp(detalhes[i]);
            ser.setTxtValorServicoPp(valores[i]);
            ser.setImgServicoPp(imageBytes);
            ser.setCodigoLoja(loja.getCodigLoja());
            ser.setCodigo(daoLocalService.inserirService(ser));
        }
    }

    private void adicionarAssistencia() {
        ObjPerfil perfil = new ObjPerfil();
        ObjCardLoja loja = new ObjCardLoja();
        ObjEndereco endereco = new ObjEndereco();

        perfil.setNome("Ricardo Freitas");
        perfil.setEmail("ricardo.assistencia@exemplo.com");
        perfil.setUsuario("TechRicardo");
        perfil.setSenha("123assist");
        perfil.setTemLoja(true);
        perfil.setCelular("11988885555");
        perfil.setCodigo(daoLocalPerfil.inserirPerfil(perfil));

        imgLojaInicial.setImageResource(R.drawable.foto_imagem);
        imageBytes = imageViewToByte(imgLojaInicial);

        loja.setNomeLoja("Conserta Tech");
        loja.setImgLoja(imageBytes);
        loja.setCpfCnpj("48720193000123");
        loja.setDescricao("Manutenção de celulares e notebooks.");
        loja.setTemEndereco(true);
        loja.setTemServicos(true);
        loja.setCodUsuario(perfil.getCodigo());
        loja.setCodigLoja(daoLocalLoja.inserirLoja(loja));

        endereco.setCepCnpj(77889900);
        endereco.setEstado("PR");
        endereco.setCidade("Curitiba");
        endereco.setBairro("Batel");
        endereco.setRua("Av. da Tecnologia");
        endereco.setNumero(123);
        endereco.setComplemento("Sala 202");
        endereco.setCodigoLoja(loja.getCodigLoja());
        endereco.setCodigo(daoLocalEndereco.inserirEndereco(endereco));

        String[] nomes = {"Troca de tela celular", "Formatação de notebook", "Troca de bateria", "Limpeza interna", "Backup de dados"};
        String[] detalhes = {
                "Substituição de tela quebrada ou trincada.",
                "Formatação com instalação do Windows.",
                "Substituição por bateria original.",
                "Remoção de poeira e resfriamento.",
                "Salvamento de arquivos e restauração."
        };
        String[] valores = {"250,00", "150,00", "180,00", "100,00", "80,00"};

        for (int i = 0; i < nomes.length; i++) {
            ObjCardServicoPp ser = new ObjCardServicoPp();
            ser.setTxtNomeServicoPp(nomes[i]);
            ser.setTxtDetalhesServicoPp(detalhes[i]);
            ser.setTxtValorServicoPp(valores[i]);
            ser.setImgServicoPp(imageBytes);
            ser.setCodigoLoja(loja.getCodigLoja());
            ser.setCodigo(daoLocalService.inserirService(ser));
        }
    }







}