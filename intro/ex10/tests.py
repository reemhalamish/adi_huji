import wikinetwork
import os

def test_ral():
    os.chdir('.')
    lines = ["raspberrypi\twatermelon\n" for i in range(5)]
    with open('temp.txt', 'w') as temp:
        for line in lines:
            temp.write(line)

    result = wikinetwork.read_article_links('temp.txt')
    assert len(result) == len(lines)

    for line in result:
        assert line == ('raspberrypi', 'watermelon')

    os.remove('temp.txt')


tests = [test_ral]
for test in tests:
    print("starting {}".format(test.__name__))
    test()
    print("Done testing!!!!!!!!!!!!!!")

