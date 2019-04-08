import React, { Component } from 'react'
import './UserSummaryItem.css';
import faker from 'faker';
export default class UserSummaryItem extends Component<Props, any> {
    constructor(props: Props){
        super(props);
        this.state = {};
    }
  render() {
    return (
      <div className="user-summary-mother-div">
          <div className="user-avatar-div">
            <img src={faker.image.avatar()}/>
          </div>
        <div className="user-item-info">
            <p>{this.props.name}</p>
            <p>{this.props.jobTitle}</p>
        </div>
      </div>
    )
  }
}
interface Props{
    name: string;
    jobTitle: string;
}