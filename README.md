Para os grpcs:

No teste fiz um design simples em que um cliente manda um olá e recebe de volta um olá com o nome que enviou. Este serviço está definido no servidor e implementado em GreeterImpl, que diz o que fazer no caso de receber 
uma mensagem de olá.
No main do lado do servidor, este apenas começa o servidor que fica à espera de mensagens de olá

No nosso caso, o cliente acho que vai ser a APP, que vai estar a interagir com o servidor (nó). Quando recebermos mensagens suficientes de transações que vamos guardando (estas transações todas comunicadas
com GRPC do cliente para o servidor), eventualmente cria um bloco e começamos a minerar
Quando acabar a mineração este bloco é propagado pela rede com um propagate block (chamada de RPC)
Ao mesmo tempo podemos estar a receber também mensagens de outros nós

Portanto ficará tudo dentro do Impl que vai definir o que fazer com cada mensagem de outros nós e vai definir também como enviar mensagens para outros nós

Aqui está um bom exemplo de como isto funciona
https://grpc.io/docs/languages/java/basics/

Nos próximos passos vou começar a definir os métodos -> ping, get blockhain, etc 
