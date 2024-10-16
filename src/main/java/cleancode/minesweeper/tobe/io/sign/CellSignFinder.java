package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapshot;

import java.util.List;

public class CellSignFinder {

    public String findCellSignFrom(CellSnapshot snapshot) {
        List<CellSignProvidable> cellSignProviders = List.of(
                new EmptyCellSignProvider(),
                new FlagCellSignProvider(),
                new LandMineCellSignProvider(),
                new NumberCellSignProvider(),
                new UncheckedCellSignProvider()
        );

        return cellSignProviders.stream()
                .filter(provider -> provider.supports(snapshot))
                .findFirst()
                .map(provider -> provider.provide(snapshot))
                .orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 셀입니다."));
    }
}
