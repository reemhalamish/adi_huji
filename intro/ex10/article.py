###############################################################
# FILE : ex10.py
# WRITER : Adi Brock , abrock , 321018962
# EXERCISE : intro2cs ex10 2016-2017
# DESCRIPTION:
###############################################################


class Article:
    def __init__(self, article_name):
        self.__title = article_name
        self.__neighbors = set()  # /dict()/set()

    def __len__(self):
        return len(self.__neighbors)

    def __contains__(self, article):
        return article in self.__neighbors

    def __repr__(self):
        lst_of_neighbor_titles = []
        for article in self.__neighbors:
            title = article.get_title()
            lst_of_neighbor_titles.append(title)
        return str((self.__title, lst_of_neighbor_titles))

    def add_neighbor(self, neighbor):
        self.__neighbors.add(neighbor)

    def get_title(self):
        return self.__title

    def get_neighbors(self):
        return self.__neighbors
