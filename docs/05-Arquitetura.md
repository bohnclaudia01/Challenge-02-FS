# Arquitetura do Sistema

## Visão Geral

O sistema foi desenvolvido com o objetivo de analisar mensagens recebidas pelo usuário e identificar possíveis características relacionadas a golpes digitais.

A arquitetura foi organizada de forma modular, separando as responsabilidades de cada etapa do processamento da mensagem. Essa divisão facilita a manutenção do código, permite futuras evoluções e torna o funcionamento do sistema mais compreensível.

O fluxo principal da aplicação segue as etapas:

**Entrada da mensagem → Validação → Normalização → Análise de riscos → Análise de confiabilidade → Cálculo do Score → Classificação final**

---

# Estrutura da Arquitetura

O sistema é dividido nos seguintes componentes:

## 1. Interface de Entrada

Responsável pela interação com o usuário.

Nesta etapa, o usuário informa a mensagem que deseja analisar por meio da entrada de texto.

Responsabilidades:

* receber a mensagem digitada ou colada pelo usuário;
* solicitar novas análises enquanto o usuário desejar;
* apresentar o resultado final da classificação.

---

## 2. Validador de Mensagem

Responsável por garantir que a entrada recebida possui condições mínimas para análise.

Antes do processamento, o sistema verifica:

* se a mensagem não é nula;
* se a mensagem não está vazia;
* se possui o tamanho mínimo necessário para análise.

Essa etapa evita processamento desnecessário e possíveis erros durante a execução.

---

## 3. Normalizador de Texto

Responsável por preparar a mensagem antes da análise dos padrões.

Essa etapa permite identificar tentativas de manipulação utilizadas para evitar filtros automáticos.

Responsabilidades:

* converter o texto para letras minúsculas;
* identificar tentativas de camuflagem;
* substituir caracteres utilizados como letras;
* remover acentos;
* remover caracteres especiais;
* padronizar espaços.

Após a normalização, a mensagem fica em um formato adequado para comparação com as regras de negócio.

---

## 4. Módulo de Análise de Risco

Responsável por identificar características associadas a golpes digitais.

O módulo utiliza os gatilhos definidos nas regras de negócio, verificando a presença de elementos como:

* solicitação de ações;
* pedidos de dados pessoais;
* solicitações de pagamento;
* promessas de benefícios;
* urgência;
* ameaças;
* presença de links.

Cada característica encontrada adiciona pontos ao Score de Risco.

---

## 5. Módulo de Análise de Confiabilidade

Responsável por identificar elementos que indicam uma comunicação possivelmente legítima.

Esse módulo aplica descontos no Score quando encontra características como:

* ausência de solicitação de dados pessoais;
* ausência de solicitação de pagamento;
* ausência de links;
* indicação de canais oficiais.

O objetivo é equilibrar a análise e reduzir classificações incorretas.

---

## 6. Sistema de Pontuação

Responsável pelo cálculo do Score de Risco.

O cálculo considera:

* penalidade por tentativas de camuflagem;
* soma dos gatilhos de risco encontrados;
* descontos de confiabilidade identificados.

O resultado desse cálculo determina o nível de risco da mensagem.

---

## 7. Classificador Final

Responsável por transformar o Score calculado em uma classificação compreensível para o usuário.

As classificações possíveis são:

* 🟢 Provavelmente Legítima;
* 🟠 Suspeita;
* 🔴 Provavelmente Golpe.

---

# Fluxo de Funcionamento

O funcionamento do sistema ocorre da seguinte forma:

1. O usuário insere uma mensagem para análise;
2. O sistema valida se a mensagem pode ser processada;
3. O texto passa pelo processo de normalização;
4. São identificadas características suspeitas;
5. São aplicados os descontos de confiabilidade;
6. O Score de Risco é calculado;
7. A mensagem recebe uma classificação final.

---

# Possíveis Evoluções da Arquitetura

A arquitetura atual permite futuras melhorias, como:

* criação de uma interface gráfica;
* armazenamento de mensagens analisadas;
* criação de uma base de dados de golpes conhecidos;
* implementação de aprendizado de máquina;
* criação de um sistema de denúncia de mensagens suspeitas;
* integração com aplicativos de mensagens.
