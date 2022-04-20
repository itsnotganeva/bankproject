package by.ganevich.io.commands;

import by.ganevich.csv.archiver.Archiver;
import by.ganevich.csv.exportCsv.CsvExporter;
import by.ganevich.io.CommandDescriptor;
import by.ganevich.io.CommandResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Getter
@Slf4j
@RequiredArgsConstructor
public class ExportCsvCommand extends BaseCommand{
    private final String commandName = "exportCsv";

    private final Archiver archiver;

    @Autowired
    private List<CsvExporter> exporters;

    @Override
    public String getDescriptionValue() {
        String description = "exportCsv";
        return description;
    }

    @Override
    public CommandResult doExecute(Map<String, String> parameters) throws IOException {

        log.info("Export csv command is called");

        Set<File> files = new HashSet<>();

        for (CsvExporter exporter : exporters) {
            files.add(exporter.exportCsv());
        }

        archiver.pack(files);

        CommandResult commandResult = new CommandResult();
        commandResult.setResult("Export is complete!");

        log.info("Export csv command is complete");

        return commandResult;
    }

    @Override
    public ICommand setDto(CommandDescriptor commandDescriptor) {
        return this;
    }
}
