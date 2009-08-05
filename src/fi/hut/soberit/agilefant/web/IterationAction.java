package fi.hut.soberit.agilefant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.Action;

import fi.hut.soberit.agilefant.business.IterationBusiness;
import fi.hut.soberit.agilefant.business.ProjectBusiness;
import fi.hut.soberit.agilefant.model.Backlog;
import fi.hut.soberit.agilefant.model.Iteration;
import fi.hut.soberit.agilefant.model.Project;
import fi.hut.soberit.agilefant.transfer.IterationMetrics;

@Component("iterationAction")
@Scope("prototype")
public class IterationAction implements
        CRUDAction {

    private static final long serialVersionUID = -448825368336871703L;

    private int iterationId;

    private Iteration iteration;

    private Backlog parentBacklog;

    private int parentBacklogId;

    private IterationMetrics iterationMetrics;

    @Autowired
    private IterationBusiness iterationBusiness;

    public String create() {
        iterationId = 0;
        iteration = new Iteration();
        return Action.SUCCESS;
    }

    public String retrieve() {
        iteration = iterationBusiness.retrieve(iterationId);
        parentBacklog = (Project) iteration.getParent();
        // Load metrics data
        iterationMetrics = iterationBusiness.getIterationMetrics(iteration);
        return Action.SUCCESS;
    }

    public String delete() {
        iteration = iterationBusiness.retrieve(iterationId);
        iterationBusiness.delete(iterationId);
        return Action.SUCCESS;
    }

    public String iterationMetrics() {
        iteration = iterationBusiness.retrieve(iterationId);
        iterationMetrics = iterationBusiness.getIterationMetrics(iteration);
        return Action.SUCCESS;
    }

    public String store() {
        iteration = this.iterationBusiness.store(iterationId, parentBacklogId, iteration);
        return Action.SUCCESS;
    }

    public int getIterationId() {
        return iterationId;
    }

    public void setIterationId(int iterationId) {
        this.iterationId = iterationId;
    }

    public Iteration getIteration() {
        return iteration;
    }

    public void setIteration(Iteration iteration) {
        this.iteration = iteration;
    }

    public int getParentBacklogId() {
        return parentBacklogId;
    }

    public void setParentBacklogId(int parentBacklogId) {
        this.parentBacklogId = parentBacklogId;
    }

    public void setIterationBusiness(IterationBusiness iterationBusiness) {
        this.iterationBusiness = iterationBusiness;
    }

    public IterationMetrics getIterationMetrics() {
        return iterationMetrics;
    }

}