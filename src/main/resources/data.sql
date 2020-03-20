INSERT INTO app_user (email, password)
VALUES ('olav.nordmann@hotmail.com', '$2a$10$.TfieO7U8dAd3v4IXpIr8.5ltZJLFHc1NFAMUcC51VuZqY1WHq932');

INSERT INTO app_user (email, password)
VALUES ('kari.nordmann@hotmail.com', '$2a$10$.TfieO7U8dAd3v4IXpIr8.5ltZJLFHc1NFAMUcC51VuZqY1WHq932'); -- passord = 123

INSERT INTO app_user (email, password)
VALUES ('nils.nordmann@hotmail.com', '$2a$10$.TfieO7U8dAd3v4IXpIr8.5ltZJLFHc1NFAMUcC51VuZqY1WHq932');

INSERT INTO gift_card (store_name, card_code, balance_int, app_user_id, expiry_date, logo_image)
VALUES ('H&M', '12345678', 50000, 1, TO_DATE('22.12.2016', 'DD/MM/YYYY'), '(Insert image URL here)');

INSERT INTO gift_card (store_name, card_code, balance_int, app_user_id, expiry_date, logo_image)
VALUES ('Clas Ohlson', '12345543', 80000, 1, TO_DATE('02/02/2022', 'dd/mm/yyyy'), '(Insert image URL here)');

INSERT INTO gift_card (store_name, card_code, balance_int, app_user_id, expiry_date, logo_image)
VALUES ('Cubus', '9934356738', 90000, 1, TO_DATE('02/02/2022', 'dd/mm/yyyy'), '(Insert image URL here)');

INSERT INTO gift_card (store_name, card_code, balance_int, app_user_id, expiry_date, logo_image)
VALUES ('Jernia', '87654321', 70000, 2, TO_DATE('02/02/2022', 'dd/mm/yyyy'), '(Insert image URL here)');

