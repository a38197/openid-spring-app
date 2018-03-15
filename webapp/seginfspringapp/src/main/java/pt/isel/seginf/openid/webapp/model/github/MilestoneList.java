package pt.isel.seginf.openid.webapp.model.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MilestoneList {
    private String owner;
    private String repository;
    private List<Milestone> milestones;

    public MilestoneList(String owner, String repository, List<Milestone> milestones) {
        this.owner = owner;
        this.repository = repository;
        this.milestones = milestones;
    }

    public static MilestoneList fromJson(String owner, String repository, String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Milestone> list = objectMapper.readValue(jsonData, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Milestone.class));
            return new MilestoneList(owner, repository, list);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String getOwner() {
        return owner;
    }

    public String getRepository() {
        return repository;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public List<Milestone> getOrderedMilestones() {
        final ArrayList<Milestone> temp = new ArrayList<>(new ArrayList<>(milestones));
        temp.sort(Comparator.comparingInt(Milestone::getNumber));
        return temp;
    }

    @Override
    public String toString() {
        return "MilestoneList{" +
                "owner='" + owner + '\'' +
                ", repository='" + repository + '\'' +
                ", milestones=" + milestones +
                '}';
    }
}
