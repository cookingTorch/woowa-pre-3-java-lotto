package lotto;

import lotto.model.Lotto;
import lotto.model.Validator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    @Test
    void buyLottoByNotNumber() {
        Validator validator = new Validator();
        assertThatThrownBy(() -> validator.validateCost("8a000", 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @Test
    void buyLottoByNotThousands() {
        Validator validator = new Validator();
        assertThatThrownBy(() -> validator.validateCost("8900", 1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또의 숫자들을 문자열 배열로 전환")
    @Test
    void numberListToStringArray() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 14, 25, 36));
        String[] lottoNumbers = new String[] {"1", "2", "3", "14", "25", "36"};
        assertThat(lotto.stringLotto())
                .containsExactly(lottoNumbers);
    }

    @DisplayName("당첨 번호가 숫자가 아니면 예외가 발생한다.")
    @Test
    void answerNotNumber() {
        Validator validator = new Validator();
        assertThatThrownBy(() -> validator.validateAnswer("1a", 1, 45))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호가 범위를 벗어나면 예외가 발생한다.")
    @Test
    void answerOutOfRange() {
        Validator validator = new Validator();
        assertThatThrownBy(() -> validator.validateAnswer("46", 1, 45))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 숫자가 아니면 예외가 발생한다.")
    @Test
    void bonusNotNumber() {
        Validator validator = new Validator();
        assertThatThrownBy(() -> validator.validateBonus("b2", 1, 45))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다.")
    @Test
    void bonusOutOfRange() {
        Validator validator = new Validator();
        assertThatThrownBy(() -> validator.validateBonus("b2", 1, 45))
                .isInstanceOf(IllegalArgumentException.class);
    }
}