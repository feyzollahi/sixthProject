import React, { Component } from 'react'
import './ProjectSummaryItem.css';
import faker from 'faker';
import ProjectSummaryItemSkill from './ProjectSummaryItemSkill';
export default class ProjectSummaryItem extends Component<Props, State> {
    constructor(props: Props){
        super(props);
        this.state = {};
    }
  render() {
    return (
      <div className="project-summary-mother-div">
        <div className="main-window">
          <div className="rtl img-div">
            <img className="project-image" src={faker.image.business()}/>
            
          </div>
          <div className="project-info">
            <div className="project-title-time">
              <p className="project-title">دیجیکالا</p>
              <p className="project-time">زمان باقی مانده:</p>
            </div>
            <p className="project-item-description">سلام ، من نیاز به اپ موبایل اندروید و iOs دارم که 3 تا آدرس رو برای من به کمک webview لود کنه. ظاهر اپ رو به صورت تصویر گذاشتم . فقط : ...</p>
            <p className="project-budget">بودجه: ۲۵۰۰تومان</p>
            <div className="project-item-skills">
              <p>مهارت ها:</p>
              <div className="project-item-skill-items">
                  <ProjectSummaryItemSkill name="javascript"/>
                  <ProjectSummaryItemSkill name="HTML"/>

              </div>
            </div>
          </div>
        </div>
        
      </div>
    )
  }
}
interface Props{
    deadline: number;
    budget: number;
    id: string;
    description: string;
    title: string;
    skills: string[];
    imageUrlText: string;
}
interface State{

}