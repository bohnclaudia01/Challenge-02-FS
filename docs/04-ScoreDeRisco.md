# Sistema de Pontuação

O sistema utiliza um **Score de Risco**, calculado a partir da soma de evidências encontradas na mensagem.

O cálculo é dividido em três etapas:

1. Aplicação da **Penalidade por Camuflagem**;
2. Soma dos pontos referentes aos **Gatilhos de Risco**;
3. Aplicação dos **Descontos de Confiabilidade**.

Ao final do processo, o Score de Risco é utilizado para classificar a mensagem como **Provavelmente Legítima**, **Suspeita** ou **Provavelmente Golpe**.

---

# 1. Penalidade por Camuflagem

Antes de analisar o conteúdo da mensagem, o sistema verifica se existem tentativas de ocultar palavras utilizando números ou caracteres especiais.

Cada tentativa encontrada aumenta a suspeita inicial da mensagem.

| Condição                        | Pontuação |
| ------------------------------- | --------: |
| Nenhuma tentativa de camuflagem |    **+0** |
| Entre 1 e 2 tentativas          |    **+2** |
| 3 ou mais tentativas            |    **+4** |

---

# 2. Gatilhos de Risco

Após a normalização do texto, o sistema procura categorias de comportamento normalmente presentes em golpes digitais.

Cada categoria encontrada adiciona pontos ao Score de Risco.

| Categoria                     | Pontuação |
| ----------------------------- | --------: |
| Solicitação de Ação           |    **+2** |
| Solicitação de Dados Pessoais |    **+3** |
| Solicitação de Pagamento      |    **+2** |
| Promessas e Benefícios        |    **+2** |
| Linguagem de Urgência         |    **+1** |
| Ameaças                       |    **+2** |
| Presença de Links             |    **+3** |

Cada categoria é contabilizada apenas uma vez, independentemente da quantidade de palavras encontradas dentro dela.

---

# 3. Desconto de Confiabilidade

Após calcular os indícios de risco, o sistema procura características normalmente presentes em comunicações legítimas.

Quando essas características são identificadas, o Score de Risco é reduzido.

| Característica              | Pontuação |
| --------------------------- | --------: |
| Não solicita dados pessoais |    **−2** |
| Não solicita pagamento      |    **−2** |
| Não possui links            |    **−1** |
| Indica canais oficiais      |    **−2** |

Esses descontos têm como objetivo reduzir falsos positivos, tornando a classificação mais equilibrada.

---

# 4. Cálculo do Score

O Score Final é obtido pela seguinte sequência de processamento:

1. O sistema inicia o Score com a penalidade por camuflagem (quando existir);
2. Soma os pontos de cada categoria de risco encontrada;
3. Aplica os descontos de confiabilidade;
4. Classifica a mensagem de acordo com a pontuação final.

---

# 5. Classificação Final

Após o cálculo do Score, a mensagem recebe uma das seguintes classificações:

|           Score Final | Classificação             |
| --------------------: | ------------------------- |
|       **Até 1 ponto** | 🟢 Provavelmente Legítima |
|   **De 2 a 5 pontos** | 🟠 Suspeita               |
| **Acima de 5 pontos** | 🔴 Provavelmente Golpe    |

---

# Exemplo de Funcionamento

Considere a seguinte mensagem:

> "Clique no link e confirme seu CPF para evitar o bloqueio da sua conta."

Durante a análise, o sistema identifica:

* Solicitação de Ação (**+2**)
* Dados Pessoais (**+3**)
* Presença de Link (**+3**)
* Ameaça (**+2**)

Caso a mensagem não apresente características de confiabilidade, o Score Final será:

**0 + 2 + 3 + 3 + 2 = 10 pontos**

Resultado:

**🔴 Provavelmente Golpe**
