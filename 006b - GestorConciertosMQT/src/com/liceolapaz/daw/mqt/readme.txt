Estructura del proyecto:

1. Entrada (abstracta):

Entrada addEntrada(): este método se sobreescribirá en cada clase heredera, y servirá para crear una nueva entrada. A través del constructor de la clase y atributos auxiliares,
                      devolverá una nueva entrada. Tiene que mostrar el precio antes de pedir confirmación. Si no se completa, devolvería null.

mostrarEntrada(): este método sirve para mostrar una entrada existente, a partir de un atributo de la clase Asistente. Valorar si sobreescribirlo para cada entrada o hacerlo universal.

1.1 Entrada - Gradas
1.2 Entrada - Pista
1.3 Entrada - Backstage


2. Asistente:

Asistente addAsistente(): este método servirá para crear un nuevo asistente. A través del constructor de la clase y atributos auxiliares, devolverá un nuevo asistente.
                          Aquí se comprobará que el email no esté repetido y el asistente sea mayor de edad, en cuyo caso se devolvería null.

3. Gestor (Main):

listarEntradasCompradas(): a partir de un toString(), este método devolvería todas las entradas que se han pedido, junto a los datos de la persona que las tiene.

mostrarEntradasDisponibles(): este método tendría que devolver el número de entradas disponibles, llamando a los contadores que tendrá esta clase.