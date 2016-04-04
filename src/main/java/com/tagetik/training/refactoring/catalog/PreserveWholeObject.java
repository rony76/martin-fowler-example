package com.tagetik.training.refactoring.catalog;

import java.util.Date;

public class PreserveWholeObject {
    void before() {
        DatiModifica datiModifica = getDatiModifica();

        String utente = datiModifica.utente;
        Date dataModifica = datiModifica.dataModifica;
        createSaldo(22.5, Conto.Immobili, utente, dataModifica);
    }

    void after() {
        DatiModifica datiModifica = getDatiModifica();

        createSaldo(22.5, Conto.Immobili, datiModifica);
    }

    private void createSaldo(double importo, Conto conto, DatiModifica datiModifica) {
        // do something here
    }

    private DatiModifica getDatiModifica() {
        return null;
    }

    private void createSaldo(double importo, Conto conto, String utente, Date dataModifica) {
        // do something here
    }

}
