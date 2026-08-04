//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.text.Normalizer;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


// ==========================================
// 1. RESULTADO E NORMALIZADOR AVANÇADO
// ==========================================


// Classe auxiliar para transportar o texto limpo e a penalidade por truques de escrita
class ResultadoNormalizacao {
    private final String textoLimpo;
    private final int pontosPenalidade;


    public ResultadoNormalizacao(String textoLimpo, int pontosPenalidade) {
        this.textoLimpo = textoLimpo;
        this.pontosPenalidade = pontosPenalidade;
    }


    public String getTextoLimpo() { return textoLimpo; }
    public int getPontosPenalidade() { return pontosPenalidade; }
}


class NormalizadorTexto {


    public static ResultadoNormalizacao normalizarEAnalisarDivergencias(String texto) {
        if (texto == null) return new ResultadoNormalizacao("", 0);


        String original = texto.toLowerCase();
        int divergencias = 0;


        // Step 1: Identifica tentativas de camuflagem (Leet Speak e símbolos em locais suspeitos)
        String[] simbolosSuspeitos = {"@", "!", "1", "3", "0", "$"};
        for (String simbolo : simbolosSuspeitos) {
            if (original.contains(simbolo)) {
                divergencias++;
            }
        }


        // Step 2: Substitui 'Leet Speak' por letras normais
        String resultado = original.replace("@", "a")
                .replace("!", "i")
                .replace("1", "i")
                .replace("3", "e")
                .replace("0", "o")
                .replace("$", "s");


        // Step 3: Remove acentos legítimos (Ex: "prêmio" -> "premio")
        String textoNormalizado = Normalizer.normalize(resultado, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        resultado = pattern.matcher(textoNormalizado).replaceAll("");


        // Step 4: Limpa pontuações e caracteres especiais mantendo apenas letras, números e espaços
        resultado = resultado.replaceAll("[^a-z0-9\\s]", " ");


        // Step 5: Remove espaços duplos ou sobressalentes
        resultado = resultado.replaceAll("\\s+", " ").trim();


        // Step 6: Calcula a penalidade inicial por tentativas de evasão do filtro
        int penalidadeInicial = 0;
        if (divergencias >= 3) {
            penalidadeInicial = 4; // Vários truques detectados (+4 risco)
        } else if (divergencias >= 1) {
            penalidadeInicial = 2; // Pelo menos um truque detectado (+2 risco)
        }


        return new ResultadoNormalizacao(resultado, penalidadeInicial);
    }
}


// ==========================================
// 2. ENUMS (REGRAS DE NEGÓCIO E ÍNDICES)
// ==========================================


enum GatilhoRisco {
    SOLICITACAO_ACAO(2, List.of("clique", "acesse", "confirme", "envie", "atualize", "baixe", "digite", "regularize",  "instale", "baixe o app", "compartilhe", "valide, copie o codigo", "responda este sms", "pague agora, ative"
    )),
    DADOS_PESSOAIS(3, List.of("cpf", "rg", "senha", "token", "codigo", "pin", "cvv", "cartao", "chave de seguranca", "biometria", "foto da cnh", "comprovante de residencia", "dados bancarios", "selfie", "confirmacao de dados"
    )),
    PAGAMENTO(2, List.of("pix", "taxa", "transferencia", "pagamento", "boleto", "fatura", "taxa de liberacao", "taxa alfandegaria", "custas cartorarias", "ted", "doc", "deposito", "reembolso", "recarga", "chave pix"
    )),
    PROMESSA(2, List.of("voce ganhou", "premio", "sorteio", "reembolso", "cashback","renda extra", "trabalho home office", "ganhos diarios", "credito aprovado", "emprestimo facilitado", "lucro garantido", "resgate de pontos", "saldo disponivel", "indique e ganhe")),
    URGENCIA(1, List.of("urgente", "hoje", "imediato", "imediatamente", "agora", "prazo final", "expira", "ultimas horas", "vence hoje", "expira em breve", "so hoje", "poucas unidades", "atendimento imediato", "prazo esgotando")),
    AMEACA(2, List.of("bloqueada", "suspensa", "cancelada", "ultimo aviso", "processo", "serasa", "conta bloqueada", "cartao cancelado", "penhora", "multa", "notificacao judicial", "busca e apreensao", "bloqueio de bens", "bloqueio de cpf", "irregularidade fiscal", "receita federal"
    )),
    POSSUI_LINK(3, List.of("http", "https", "www", "bit ly", "t me", "tinyurl", "wa me", "cutt ly", "is gd", "rebrand ly", "shorturl at", "gg gg", "linktr ee", "ow ly"
    ));


    private final int pontos;
    private final List<String> indices;


    GatilhoRisco(int pontos, List<String> indices) {
        this.pontos = pontos;
        this.indices = indices;
    }


    public int getPontos() {
        return pontos;
    }


    public boolean presenteEm(String textoNormalizado) {
        return indices.stream().anyMatch(textoNormalizado::contains);
    }
}


enum GatilhoConfiabilidade {
    NAO_PEDE_DADOS_PESSOAIS(-2, GatilhoRisco.DADOS_PESSOAIS),
    NAO_PEDE_PAGAMENTO(-2, GatilhoRisco.PAGAMENTO),
    NAO_POSSUI_LINK(-1, GatilhoRisco.POSSUI_LINK),
    CANAIS_OFICIAIS(-2, List.of("site oficial", "aplicativo oficial", "app oficial", "agencia", "central de atendimento"));


    private final int pontos;
    private final List<String> indices;
    private final GatilhoRisco gatilhoInverso;


    GatilhoConfiabilidade(int pontos, List<String> indices) {
        this.pontos = pontos;
        this.indices = indices;
        this.gatilhoInverso = null;
    }


    GatilhoConfiabilidade(int pontos, GatilhoRisco gatilhoInverso) {
        this.pontos = pontos;
        this.indices = List.of();
        this.gatilhoInverso = gatilhoInverso;
    }


    public int getPontos() {
        return pontos;
    }


    public boolean ehValido(String textoNormalizado) {
        if (gatilhoInverso != null) {
            return !gatilhoInverso.presenteEm(textoNormalizado);
        }
        return indices.stream().anyMatch(textoNormalizado::contains);
    }
}


// ==========================================
// 3. VALIDADOR DE ENTRADA
// ==========================================


class ValidadorDeMensagem {
    public static void validar(String mensagem) {
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("A mensagem não pode ser nula ou vazia.");
        }
        if (mensagem.trim().length() < 5) {
            throw new IllegalArgumentException("A mensagem é muito curta para ser analisada (mínimo 5 caracteres).");
        }
    }
}


// ==========================================
// 4. ANALISADOR DE MENSAGENS
// ==========================================


class AnalisadorDeMensagem {


    public String analisar(String mensagem) {
        // Step 1: Valida requisitos mínimos da mensagem
        ValidadorDeMensagem.validar(mensagem);


        // Step 2: Normaliza o texto e avalia divergências/ofuscação
        ResultadoNormalizacao normalizacao = NormalizadorTexto.normalizarEAnalisarDivergencias(mensagem);
        String textoNormalizado = normalizacao.getTextoLimpo();


        // Step 3: O SCORE JÁ INICIA COM PONTOS SE HOUVER TENTATIVA DE ENGANAR O FILTRO!
        int score = normalizacao.getPontosPenalidade();


        // Step 4: Pergunta de RISCO (+)
        for (GatilhoRisco risco : GatilhoRisco.values()) {
            if (risco.presenteEm(textoNormalizado)) {
                score += risco.getPontos();
            }
        }


        // Step 5: Perguntas de CONFIABILIDADE (-)
        for (GatilhoConfiabilidade confiabilidade : GatilhoConfiabilidade.values()) {
            if (confiabilidade.ehValido(textoNormalizado)) {
                score += confiabilidade.getPontos();
            }
        }


        // Step 6: Retorna a classificação final
        return classificar(score);
    }


    private String classificar(int score) {
        if (score <= 1) {
            return "🟢 Provavelmente Legítima (Score: " + score + ")";
        } else if (score <= 5) {
            return "🟠 Suspeita (Score: " + score + ")";
        } else {
            return "🔴 Provavelmente Golpe (Score: " + score + ")";
        }
    }
}


// ==========================================
// 5. INTERFACE INTERATIVA COM SCANNER
// ==========================================


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnalisadorDeMensagem analisador = new AnalisadorDeMensagem();


        System.out.println("==================================================");
        System.out.println("   DETECTOR DE MENSAGENS SUSPEITAS E GOLPES       ");
        System.out.println("==================================================");


        boolean continuar = true;


        while (continuar) {
            System.out.println("\nDigite ou cole a mensagem que deseja analisar:");
            System.out.print("> ");
            String mensagemUsuario = scanner.nextLine();


            try {
                String resultado = analisador.analisar(mensagemUsuario);


                System.out.println("\n--- RESULTADO DA ANÁLISE ---");
                System.out.println("Classificação: " + resultado);
                System.out.println("----------------------------");


            } catch (IllegalArgumentException e) {
                System.out.println("\n⚠️  " + e.getMessage());
            }


            System.out.print("\nDeseja analisar outra mensagem? (S/N): ");
            String resposta = scanner.nextLine().trim().toUpperCase();


            if (!resposta.equals("S") && !resposta.equals("SIM")) {
                continuar = false;
            }
        }


        System.out.println("\nPrograma encerrado. Obrigado!");
        scanner.close();
    }
}

