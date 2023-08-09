
  Feature:

    Scenario:

      Given Kullanici "jPHBaseUrl" base URL'ini kullanir
      And Path parametreleri icin "posts/23" kullanir
      When jPH server a GET request gonderir ve testleri yapmak icin response degerini kaydeder
      Then jPH respons'da status degerinin 200
      And jPH respons'da content type degerinin "application/json; charset=utf-8"
      