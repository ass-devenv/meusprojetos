using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace ReadWriteFile
{
    public partial class Form1 : Form
    {
        string[] array;
        int tamArray;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void btnReadWrite_Click(object sender, EventArgs e)
        {
            
        }

        private void btnReadWrite_Click_1(object sender, EventArgs e)
        {
            label1.BackColor = System.Drawing.Color.Red;

            DirectoryInfo di = new DirectoryInfo("C:\\rock_in_rio");
            FileInfo[] file = di.GetFiles("*.txt");

            foreach (FileInfo fileinfo in file)
            {
                StreamReader sr = new StreamReader("C:\\rock_in_rio\\" + fileinfo);

                array = File.ReadAllLines("C:\\rock_in_rio\\" + fileinfo);
                tamArray = array.Length;

                //** Montando Cabeçalho do RPS
                string filetxt = sr.ReadLine();
                string[] colunaTxt = filetxt.Split(';');
                CreateCabecalho(colunaTxt);

                //** Montando linha Detalhe do RPS
                while (!sr.EndOfStream)
                {
                    string linhaDetalhe = sr.ReadLine();

                    if (linhaDetalhe.Trim() != "")
                    {
                        string[] colunas;
                        colunas = linhaDetalhe.Split(';');
                        CreateDetalhe(colunas);
                    }
                }

                //** Montando Rodapé do RSP
                CreateRodape(colunaTxt);
            }

            label1.BackColor = System.Drawing.Color.Green;
        }

        public static void CreateDetalhe(string[] _info)
        {
            #region variaveisRPS

            string espBranco = "              ";
            string reservado = "                              ";

            int numInscMunic;
            int detalhe_tipo_reg = 30;
            string cpfCnpjTomador;
            int identf_cpfCnpjTomador;
            int tipoRPS = 2;
            int numSerie;
            int numRPS;
            int statusRPS = 1;
            int optSimples = 1;
            int codServFederal = 0;
            int codServMunicipal = 0;
            int aliquota = 0;
            int valorServico;
            int valorDevolucao;
            int valorISS = 0;
            int issRetido = 1;
            string dataCompetencia;
            int serieRPSsubstituido;
            int numRPSsubs = 0;
            string descricaoServico;
            DateTime data;

            #endregion

            FileStream fs = new FileStream("C:\\file_rps_prefeitura\\geradoRPS01.txt", FileMode.Append);
            StreamWriter file = new StreamWriter(fs, Encoding.UTF8);

            #region Atribuindo valores as VARIAVEIS

            ////** Escrita de traz para frente... **//
            //string dadosInvert ; // Esperando um valor a ser atribuido...
            //char[] ArrayChar = dadosInvert.ToCharArray();
            //Array.Reverse(ArrayChar);

            data = DateTime.Parse(_info[2].ToString());
            string invertData = data.ToString("yyyy/MM/dd").Replace("/", "");

            numInscMunic = int.Parse(_info[5].ToString());

            numSerie = int.Parse(_info[1].ToString());
            numRPS = int.Parse(_info[0].ToString());

            cpfCnpjTomador = _info[3].ToString();
            valorServico = int.Parse(_info[7].ToString().Replace(",", "").Replace(".", ""));
            valorDevolucao = int.Parse(_info[7].ToString().Replace(",", "").Replace(".", ""));
            valorISS = int.Parse(_info[7].ToString().Replace(",", "").Replace(".", ""));

            dataCompetencia = invertData;          //_info[2].ToString().Replace("/", "");
            serieRPSsubstituido = int.Parse(_info[1].ToString());
            numRPSsubs = int.Parse(_info[0].ToString());
            descricaoServico = _info[9].ToString();

            #endregion

            //Verificando tipo...
            if (cpfCnpjTomador.Length == 11)
            {
                identf_cpfCnpjTomador = 1; //CPF
            }
            else if (cpfCnpjTomador.Length == 14)
            {
                identf_cpfCnpjTomador = 2; //CNPJ
            }
            else
            {
                identf_cpfCnpjTomador = 3; //Não Informado
            }

            string linhaDetalhe = detalhe_tipo_reg.ToString() + tipoRPS.ToString() + numSerie.ToString("00000") + numRPS.ToString("000000000000000") + invertData + statusRPS.ToString() +
                            identf_cpfCnpjTomador + cpfCnpjTomador + optSimples.ToString("0") + codServFederal.ToString("0000") +
                            espBranco +
                            codServMunicipal.ToString("00000000000000000000") + aliquota.ToString("00000") + valorServico.ToString("000000000000000") +
                            valorDevolucao.ToString("000000000000000") +
                            reservado +
                            valorISS.ToString("000000000000000") + issRetido.ToString("0") + dataCompetencia + serieRPSsubstituido.ToString("00000") +
                            numRPSsubs.ToString("000000000000000") +
                            reservado +
                            descricaoServico.Replace("-","|") + "\r\n";

            file.WriteLine(linhaDetalhe);
            file.Flush();
            file.Close();
            fs.Close();
        }

        public static void CreateCabecalho(string[] _info)
        {
            FileStream fs = new FileStream("C:\\file_rps_prefeitura\\geradoRPS01.txt", FileMode.Append);
            StreamWriter file = new StreamWriter(fs, Encoding.UTF8);

            int tipo_registro = 10;
            int versao_arq = 3;
            int identif_cpfCnpj;
            string dataInicioPeriodoTransfArq;
            string dataFimPeriodoTransfArq;
            int numInscMunic = int.Parse(_info[5].ToString());

            DateTime data = DateTime.Parse(_info[2].ToString());

            string invertData = data.ToString("yyyy/MM/dd").Replace("/", "");


            //Verificando tipo CPF ou CNPJ
            if (_info[3].Length != 11)
            {
                identif_cpfCnpj = 2; //CNPJ
            }
            else
            {
                identif_cpfCnpj = 1; //CPF
            }


            dataInicioPeriodoTransfArq = invertData;
            dataFimPeriodoTransfArq = invertData;

            string linhaRPS = tipo_registro.ToString("00") + versao_arq.ToString("000") + identif_cpfCnpj + _info[3].ToString() + numInscMunic.ToString("000000000000000") +
                         dataInicioPeriodoTransfArq + dataFimPeriodoTransfArq + "\r\n";

            file.WriteLine(linhaRPS);
            file.Flush();
            file.Close();
            fs.Close();

        }

        public static void CreateRodape(string[] _info)
        {
            FileStream fs = new FileStream("C:\\file_rps_prefeitura\\geradoRPS01.txt", FileMode.Append);
            StreamWriter file = new StreamWriter(fs, Encoding.UTF8);

            int tipoRegRodape = 90;
            int numLinhaDetalheArq = 0;
            int valorTotalServico = 0;
            int valorDeducaoArq = 0;
            int valorTotalDescCondArq = 0;
            int valorTotalDescInconArq = 0;

            string linhaRodape = tipoRegRodape.ToString("00") + numLinhaDetalheArq.ToString("00000000") +
                             valorTotalServico.ToString("000000000000000").Replace(".", "") +
                             valorDeducaoArq.ToString("000000000000000").Replace(",", "").Replace(".", "") +
                             valorTotalDescCondArq.ToString("000000000000000").Replace(",", "").Replace(".", "") +
                             valorTotalDescInconArq.ToString("000000000000000").Replace(",", "").Replace(".", "") + "\r\n";

            file.WriteLine(linhaRodape);
            file.Flush();
            file.Close();
            fs.Close();
        }

        private void Form1_Load_1(object sender, EventArgs e)
        {

        }

            }

}