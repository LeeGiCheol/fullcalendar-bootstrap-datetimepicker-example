package me.gicheol.calendar.example;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final ModelMapper modelMapper;


    @Transactional(readOnly = true)
    public List<CalendarForm> calendarList() {
        List<Calendar> all = calendarRepository.findAll();
        return all.stream()
                        .map(c -> modelMapper.map(c, CalendarForm.class))
                        .collect(Collectors.toList());
    }

    public CalendarForm create(Calendar calendar) {
        Calendar save = calendarRepository.save(calendar);
        return modelMapper.map(save, CalendarForm.class);
    }

    public CalendarForm createOrUpdate(CalendarForm calendarForm) {
        Calendar calendar = modelMapper.map(calendarForm, Calendar.class);
        Calendar save = calendarRepository.save(calendar);
        return modelMapper.map(save, CalendarForm.class);
    }

    public int calendarDelete(CalendarForm calendarForm) {
        return calendarRepository.calendarDelete(calendarForm.getId());
    }

}
