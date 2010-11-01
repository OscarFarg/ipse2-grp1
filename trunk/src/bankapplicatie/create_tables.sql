	    //create tabellen.
	    /*
	    Statement statement =  deDbConnectie.createStatement();

	    statement.execute("drop table persoon");

	    statement.execute(
		    "CREATE TABLE persoon ( " +
		    "bsn VARCHAR(9) CONSTRAINT NN_klant_bsn NOT NULL, " +
		    "naam VARCHAR(12) CONSTRAINT NN_klant_naam NOT NULL, " +
		    "CONSTRAINT PK_Klant PRIMARY KEY (bsn) " +
		    ")"
	    );


	    statement.execute(
	    		"CREATE TABLE rekening (" +
	    		"nr VARCHAR(9) CONSTRAINT NN_rekening_nr NOT NULL, " +
	    		"saldo NUMERIC(5,2) CONSTRAINT NN_rekening_saldo NOT NULL, " +
	    		"bsn VARCHAR(9) CONSTRAINT NN_rekening_bsn NOT NULL, " +
	    		"CONSTRAINT PK_rekening PRIMARY KEY (nr)" +
	    		")"
	    );

	    statement.execute("" +
	    		"ALTER TABLE rekening ADD CONSTRAINT rekening_persoon " +
	    		"FOREIGN KEY (bsn) REFERENCES persoon (bsn)"
	    		);
	    statement.close();
	     */
