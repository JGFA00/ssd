Para os grpcs:

No teste fiz um design simples em que um cliente manda um olá e recebe de volta um olá com o nome que enviou. Este serviço está definido e implementado em GreeterImpl, que diz o que fazer no caso de receber 
uma mensagem de olá.
No main do lado do servidor, este apenas começa o servidor que fica à espera de mensagens de olá

No nosso caso, o cliente acho que vai ser a APP, que vai estar a interagir com o servidor (nó). Vamos recebendo e guardando mensagens GRPC de transações do cliente para o nó, e quando houver suficientes  cria um bloco e começamos a minerar.

Quando acabar a mineração este bloco é propagado pela rede com um propagate block (chamada de RPC)
Ao mesmo tempo podemos estar a receber também mensagens de outros nós, transações, etc, tudo isto tem que estar definido como um serviço em GRPC 

Portanto ficará tudo dentro do Impl que vai definir o que fazer com cada mensagem de outros nós e vai definir também como enviar mensagens para outros nós

Aqui está um bom exemplo de como isto funciona
https://grpc.io/docs/languages/java/basics/

Deixei também um exemplo no servidor de criar uma lista e ir adicionando os nomes que recebe e imprime. Isto em vez da lista será por exemplo a blockchain, a routing table, etc, que à medida que vamos recebendo informação noutras mensagens vamos adicionando o conteudo.

Nos próximos passos vou começar a definir os métodos -> ping, get blockhain, etc 
