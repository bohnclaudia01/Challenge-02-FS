# Regras de Negócio

A análise realizada pelo sistema é baseada em um modelo de pontuação composto por três etapas principais:

* **Soma de Riscos**
* **Desconto de Confiabilidade**
* **Penalidade por Camuflagem**

Essa abordagem foi inspirada no funcionamento de sistemas reais de detecção de spam, phishing e golpes digitais.

O princípio utilizado é que **uma mensagem não é considerada suspeita por conter apenas uma palavra específica**, mas pelo conjunto de características presentes em seu conteúdo. Da mesma forma, determinadas informações presentes em comunicações legítimas podem reduzir a suspeita, tornando a análise mais equilibrada e diminuindo a ocorrência de falsos positivos.

---

# Penalidade por Camuflagem

Antes da análise da mensagem, o sistema verifica se existem tentativas de ocultar palavras utilizando caracteres especiais ou números no lugar de letras.

Essa técnica é bastante utilizada por golpistas para tentar burlar sistemas de detecção automática.

Para impedir esse comportamento, os caracteres são normalizados antes da análise, realizando as seguintes substituições:

* **@ → a**
* **! → i**
* **1 → i**
* **3 → e**
* **0 → o**
* **$ → s**

Após essa normalização, a mensagem segue para a etapa de identificação das características suspeitas.

---

# Soma de Riscos

Nesta etapa, o sistema identifica características frequentemente presentes em golpes digitais.

Cada característica encontrada contribui para aumentar o nível de risco da mensagem.

As categorias analisadas são:

## Solicitação de Ação

Palavras ou expressões que incentivam o usuário a executar alguma ação imediatamente.

```
clique, acesse, confirme, envie, atualize, baixe, digite,
regularize, instale, baixe o app, compartilhe, valide,
copie o código, responda este SMS, pague agora, ative
```

---

## Solicitação de Dados Pessoais

Termos relacionados à solicitação de informações pessoais ou bancárias.

```
cpf, rg, senha, token, código, pin, cvv, cartão,
chave de segurança, biometria, foto da CNH,
comprovante de residência, dados bancários,
selfie, confirmação de dados
```

---

## Solicitação de Pagamento

Expressões relacionadas à cobrança de valores ou transferências financeiras.

```
pix, taxa, transferência, pagamento, boleto,
fatura, taxa de liberação, taxa alfandegária,
custas cartorárias, ted, doc, depósito,
reembolso, recarga, chave pix
```

---

## Promessas e Benefícios

Mensagens que oferecem vantagens financeiras ou recompensas para induzir o usuário.

```
você ganhou, prêmio, sorteio, reembolso,
cashback, renda extra, trabalho home office,
ganhos diários, crédito aprovado,
empréstimo facilitado, lucro garantido,
resgate de pontos, saldo disponível,
indique e ganhe
```

---

## Linguagem de Urgência

Expressões utilizadas para pressionar o usuário a agir rapidamente.

```
urgente, hoje, imediato, imediatamente,
agora, prazo final, expira,
últimas horas, vence hoje,
expira em breve, só hoje,
poucas unidades, atendimento imediato,
prazo esgotando
```

---

## Ameaças

Mensagens que utilizam medo ou intimidação para convencer o usuário.

```
bloqueada, suspensa, cancelada,
último aviso, processo,
serasa, conta bloqueada,
cartão cancelado, penhora,
multa, notificação judicial,
busca e apreensão,
bloqueio de bens,
bloqueio de CPF,
irregularidade fiscal,
Receita Federal
```

---

## Presença de Links

Identificação de links ou encurtadores frequentemente utilizados em mensagens suspeitas.

```
http, https, www,
bit ly, t me,
tinyur, wa me,
cutt ly, is gd,
rebrand ly,
shorturl at,
gg gg,
linktr ee,
ow ly
```

---

## Falso Parente ou Contato

Mensagens que simulam contato de familiares ou conhecidos utilizando um novo número.

```
troquei de número,
meu número novo,
salva meu contato novo,
apaga o número antigo,
estou sem acesso ao banco,
preciso de um favor
```

---

# Desconto de Confiabilidade

Após calcular os indícios de risco, o sistema procura elementos que costumam aparecer em comunicações legítimas.

Essas características reduzem a suspeita da mensagem, contribuindo para uma análise mais equilibrada.

As categorias consideradas são:

## Canais Oficiais

Mensagens que orientam o usuário a utilizar canais oficiais da instituição.

```
site oficial,
aplicativo oficial,
app oficial,
agência,
central de atendimento,
procure seu gerente,
fale com seu gerente,
central de relacionamento,
SAC,
atendimento ao cliente,
loja física,
caixa eletrônico,
consulte no app
```

---

## Informativos e Avisos

Mensagens meramente informativas, que comunicam um status sem solicitar ações imediatas, pagamentos ou envio de informações.

```
comprovante de compra,
fatura disponível no app,
compra aprovada,
código de rastreio,
pedido enviado,
agendamento confirmado,
lembrete de consulta,
protocolo de atendimento
```

---

## Alertas de Segurança

Mensagens em que a própria instituição reforça boas práticas de segurança e orienta o usuário a proteger seus dados.

```
não compartilhe esta senha,
não informe este código,
nunca pedimos sua senha,
não pedimos senhas,
código de uso pessoal,
se não foi você, desconsidere
```
