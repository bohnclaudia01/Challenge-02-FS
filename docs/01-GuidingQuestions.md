# Perguntas Norteadoras do Projeto

## Introdução

Durante o desenvolvimento do projeto, foram levantadas perguntas com o objetivo de compreender o problema, definir as regras de análise e orientar a construção de um sistema capaz de identificar possíveis golpes digitais.

As perguntas serviram como base para as decisões tomadas durante o desenvolvimento, principalmente na criação das regras de negócio, classificação de risco e apresentação dos resultados ao usuário.

---

# 1. Qual é o problema que estamos tentando resolver?

O projeto busca desenvolver uma solução capaz de identificar mensagens com características comuns de golpes digitais, auxiliando usuários a reconhecer possíveis tentativas de fraude antes de realizar alguma ação de risco.

O sistema tem como objetivo analisar o conteúdo das mensagens e identificar padrões associados a golpes, como solicitações financeiras, pedidos de informações pessoais, links suspeitos, senso de urgência e promessas de benefícios.

---

# 2. O sistema apenas identifica golpes ou também explica o motivo da classificação?

O sistema não apenas classifica uma mensagem como suspeita, mas também apresenta os motivos que levaram à classificação.

A explicação é importante para que o usuário compreenda quais características foram identificadas na mensagem, tornando a análise mais transparente e ajudando na educação digital.

Exemplos de justificativas apresentadas:

* presença de link suspeito;
* solicitação de dados pessoais;
* pedido de transferência ou pagamento;
* uso de linguagem de urgência;
* promessa de prêmio ou benefício.

---

# 3. O usuário poderá denunciar mensagens suspeitas?

Sim. A funcionalidade de denúncia foi considerada como uma possibilidade futura para evolução do sistema.

Embora não tenha sido desenvolvida nesta etapa do projeto, essa funcionalidade permitiria criar uma base de mensagens suspeitas, contribuindo para melhorar futuras análises e aumentar a eficiência da identificação de novos padrões de golpes.

---

# 4. Como a mensagem será enviada ao sistema?

O usuário poderá inserir a mensagem manualmente no sistema ou utilizar a opção de copiar e colar o conteúdo recebido.

A partir dessa entrada, o sistema realizará a análise das características presentes no texto e aplicará as regras de negócio definidas.

---

# 5. O sistema exibirá um nível de risco?

Sim. Após a análise, o sistema apresentará uma classificação de risco para facilitar a compreensão do usuário.

A classificação será dividida em níveis:

* *Baixo risco:* poucos ou nenhum indício de golpe identificado;
* *Médio risco:* presença de algumas características suspeitas;
* *Alto risco:* grande quantidade de elementos associados a golpes digitais.

Essa classificação é baseada no cálculo de um Score de Risco, criado a partir das características encontradas na mensagem.

---

# 6. Por que uma pessoa cai em um golpe digital?

Pessoas podem cair em golpes digitais devido a fatores como falta de conhecimento sobre ameaças virtuais, confiança excessiva em mensagens recebidas e utilização de técnicas psicológicas pelos criminosos.

Muitos golpes utilizam estratégias como:

* criação de urgência;
* medo de perder uma oportunidade;
* promessa de benefícios;
* tentativa de gerar confiança utilizando nomes de empresas conhecidas;
* solicitação de informações pessoais.

O sistema busca atuar como uma camada adicional de proteção, auxiliando o usuário na tomada de decisão.

---

# 7. Existe algum padrão entre os golpes?

Sim. Apesar de existirem diferentes tipos de golpes digitais, muitos apresentam características semelhantes.

Alguns padrões identificados foram:

* mensagens solicitando dados pessoais;
* pedidos de pagamento ou transferência;
* links desconhecidos ou suspeitos;
* mensagens com senso de urgência;
* ofertas ou recompensas inesperadas;
* tentativa de se passar por empresas ou instituições confiáveis.

Esses padrões foram utilizados como base para criação das regras de análise do sistema.

---

# 8. Toda mensagem que contém uma palavra suspeita é um golpe?

Não. A presença isolada de uma palavra suspeita não é suficiente para determinar que uma mensagem é um golpe.

A análise precisa considerar o contexto completo da mensagem, combinando diferentes características para evitar classificações incorretas.

Por esse motivo, o sistema utiliza múltiplas regras de negócio e um sistema de pontuação, em vez de depender apenas de palavras específicas.

---

# 9. Como tornar a análise mais confiável?

A confiabilidade da análise pode ser aumentada considerando diferentes fatores ao mesmo tempo.

O sistema utiliza regras de negócio que avaliam características como:

* presença de links;
* solicitação de informações sensíveis;
* pedidos financeiros;
* linguagem de pressão ou urgência;
* tentativa de induzir o usuário a realizar alguma ação.

A combinação desses fatores gera um Score de Risco mais preciso do que uma análise baseada em apenas um elemento.

---

# 10. Como apresentar o resultado ao usuário?

O resultado deve ser apresentado de maneira simples, clara e compreensível.

Além do nível de risco, o sistema deve informar quais características foram identificadas e explicar por que aquela mensagem recebeu determinada classificação.

O objetivo é evitar apenas um alerta de "golpe" ou "não golpe", oferecendo ao usuário conhecimento para avaliar situações semelhantes no futuro.

---

# 11. Como empresas legítimas se comunicam?

Empresas legítimas normalmente utilizam canais oficiais de comunicação e evitam solicitar informações sensíveis por mensagens inesperadas.

Algumas características comuns de comunicações confiáveis:

* identificação clara da empresa;
* ausência de solicitações urgentes de dados pessoais;
* utilização de canais oficiais;
* mensagens com informações consistentes;
* ausência de pressão para tomada rápida de decisão.

Esses padrões também foram considerados na análise para reduzir falsos positivos.

---

# 12. Uma única característica é suficiente para identificar um golpe?

Não. Uma única característica isolada não é suficiente para determinar que uma mensagem é fraudulenta.

Por exemplo, uma mensagem pode conter um link sem necessariamente ser um golpe. Da mesma forma, uma empresa pode utilizar linguagem de urgência em determinadas situações legítimas.

Por isso, o sistema combina diferentes evidências e calcula um Score de Risco, tornando a classificação mais confiável.