package TesteJUnit;


import org.junit.jupiter.api.Test;

import connection.SingleConnection;

class JdbcPostgres {
	
	@Test
	public void testeConexao() {
		SingleConnection.getConnection();
		
	}

}
