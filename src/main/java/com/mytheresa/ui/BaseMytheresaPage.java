package com.mytheresa.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sergei Tsarik, Maria Ramanova, Nalegach Yakov
 */
public class BaseMytheresaPage {

    private static final Logger log = LoggerFactory.getLogger(BaseMytheresaPage.class);

    private final String basePage = "https://www.mytheresa.com/";

    public String getBasePage() {
        return basePage;
    }

}
