INSERT INTO article (article_title, article_author, article_body)
VALUES ('Long Article', 'Harry', 'Lorem ipsum dolor sit amet, quodsi albucius offendit ius ne, usu an prima evertitur, unum probo meliore eu qui. Cu nec consul partem tacimates, at nam facer delenit vivendo. Te eum praesent voluptatum, eum diam concludaturque cu. Mel ex ullum vivendo honestatis. Quo ei probo nostrud temporibus, cum in velit error simul, ad pro omittam deleniti quaerendum. Eam ne labores propriae dissentiet. Odio impetus consequat an sed, solet utinam eligendi ut nec, per ne mucius sapientem. Tale eius primis ex vis, at vel sanctus epicurei inimicus, ex quo omnis munere platonem.')

INSERT INTO comments(comments_author, coments_body, article_id)
VALUES  ( 'Hermione', 'Ron you are so wrong', '6'),
        ( 'Harry', 'Guys, please be quiet', '6'),
        ('Harry', 'Good call Ron!', '6'),
        ( 'Ron', 'Little bit', '6'),
        ( 'Ron', 'LOL you are so lucky bro', '6');




INSERT INTO article (article_title, article_author, article_body)
VALUES ('NewLines Article', 'Ron', 'Lorem ipsum dolor sit amet, quodsi albucius offendit ius ne, usu an prima evertitur, unum probo meliore eu qui. Cu nec consul partem tacimates, at nam facer delenit vivendo. Te eum praesent voluptatum, eum diam concludaturque cu. Mel ex ullum vivendo honestatis.

Quo ei probo nostrud temporibus, cum in velit error simul, ad pro omittam deleniti quaerendum. Eam ne labores propriae dissentiet. Odio impetus consequat an sed, solet utinam eligendi ut nec, per ne mucius sapientem. Tale eius primis ex vis, at vel sanctus epicurei inimicus, ex quo omnis munere platonem.')