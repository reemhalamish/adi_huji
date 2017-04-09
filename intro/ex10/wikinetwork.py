###############################################################
# FILE : ex10.py
# WRITER : Adi Brock , abrock , 321018962
# EXERCISE : intro2cs ex10 2016-2017
# DESCRIPTION:
###############################################################

from article import Article

def read_article_links(file_name):
    """

    :param file_name:
    :return:
    """
    with open(file_name) as links:
        return [tuple(line.split()) for line in links.readlines()]


class WikiNetwork:
    ARTICLE_TITLE_IDX = 0
    ARTICLE_NEIGHBOR_IDX = 1

    def __init__(self, link_list):
        self.__titles_to_articles = dict()
        self.update_network(link_list)

    def __contains__(self, title):
        return title in self.__titles_to_articles

    def __len__(self):
        return len(self.__titles_to_articles)

    def __repr__(self):
        return repr(self.__titles_to_articles)

    def __getitem__(self, title):
        article = self.__titles_to_articles.get(title)
        if article is not None:
            return article
        raise KeyError(title)

    def update_network(self, link_list):
        for pair in link_list:

            article_title = pair[WikiNetwork.ARTICLE_TITLE_IDX]
            if article_title not in self.__titles_to_articles:
                article = Article(article_title)
                self.__titles_to_articles[article_title] = article

            neighbor_title = pair[WikiNetwork.ARTICLE_NEIGHBOR_IDX]
            if neighbor_title not in self.__titles_to_articles:
                neighbor_article = Article(neighbor_title)
                self.__titles_to_articles[neighbor_title] = neighbor_article

            article = self.__titles_to_articles[article_title]
            neighbor_article = self.__titles_to_articles[neighbor_title]
            if neighbor_article not in article:
                article.add_neighbor(neighbor_article)

    def page_rank(self, iters, d=0.9):
        articles_to_money = {article: 1.0 for article in self.get_articles()}
        for i in range(iters):
            transactions = []  # will hold tuples - ([lst of addressies], amnt)

            # stage 1
            for article, money in articles_to_money.items():
                money_to_give_each = d * money / len(article)
                # as formula in PDF

                transaction = article.get_neighbors(), money_to_give_each
                transactions.append(transaction)

            # stage 2:
            articles_to_money = {article: 1.0 - d
                                 for article in self.get_articles()}
            for addressies, amnt in transactions:
                for addressy in addressies:
                    articles_to_money[addressy] += amnt

        objects = sorted([(-articles_to_money[article], article.get_title())
                          for article in articles_to_money])
        # put a minus before amnt because sorted() sorts from small to big
        return [a[1] for a in objects]

    def get_articles(self):
        return list(self.__titles_to_articles.values())

    def get_titles(self):
        return list(self.__titles_to_articles)
