#############################################################
# FILE : hangman.py
# WRITER : Adi Brock , abrock , 321018962
# EXERCISE : intro2cs ex4 2016-2017
# DESCRIPTION:
#############################################################
import hangman_helper


def update_word_pattern(word, pattern, letter):
    """
    Gets a word
    :param word:
    :param pattern:
    :param letter:
    :return:
    """
    for i in range(len(word)):
        if word[i] == letter:
            pattern = pattern[:i] + letter + pattern[i+1:]
    return pattern


def is_input_valid(user_input):
    """Gets user input and returns if input is valid"""
    character = user_input[1]
    return character.isalpha() and len(character) == 1 and character.islower()


def run_single_game(words_list):
    word = hangman_helper.get_random_word(words_list)
    error_count = 0
    wrong_guess_lst = []
    guessed_letters = ''
    pattern = '_'*len(word)
    msg = hangman_helper.DEFAULT_MSG

    while error_count < hangman_helper.MAX_ERRORS and '_' in pattern:
        hangman_helper.display_state(pattern,
                                     error_count,
                                     wrong_guess_lst,
                                     msg)
        user_input = hangman_helper.get_input()
        if user_input[0] == hangman_helper.LETTER:
            if not is_input_valid(user_input):
                msg = hangman_helper.NON_VALID_MSG
                continue
            if user_input[1] in guessed_letters:
                msg = hangman_helper.ALREADY_CHOSEN_MSG + user_input[1]
                continue
            guessed_letters += user_input[1]
            if user_input[1] in word:
                pattern = update_word_pattern(word, pattern, user_input[1])
                msg = hangman_helper.DEFAULT_MSG
                continue
            # if reached here meaning letter is not in word
            wrong_guess_lst.append(user_input[1])
            error_count += 1
            msg = hangman_helper.DEFAULT_MSG
            continue
        if user_input[0] == hangman_helper.HINT:
            msg = hangman_helper.NO_HINTS_MSG

    if error_count == hangman_helper.MAX_ERRORS:
        msg = hangman_helper.LOSS_MSG + word
    else:
        msg = hangman_helper.WIN_MSG
    hangman_helper.display_state(pattern,
                                 error_count,
                                 wrong_guess_lst,
                                 msg,
                                 True)


def main():
    words_list = hangman_helper.load_words('words.txt')
    run_single_game(words_list)
    user_input = hangman_helper.get_input()
    while user_input[0] == hangman_helper.PLAY_AGAIN and user_input[1]:
        run_single_game(words_list)
        user_input = hangman_helper.get_input()


if __name__ == "__main__":
    hangman_helper.start_gui_and_call_main(main)
    hangman_helper.close_gui()







